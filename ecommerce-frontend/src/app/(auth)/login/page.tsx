
"use client";

import { FormEvent, useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { AlertCircle, LoaderCircle, ShoppingBag } from "lucide-react";
import FormInput from "@/components/common/FormInput";
import { login } from "@/services/authApi";
import type { LoginRequest } from "@/types/auth";

const LoginPage = () => {
  const router = useRouter();
  const [formData, setFormData] = useState<LoginRequest>({
    email: "",
    password: "",
  });
  const [rememberMe, setRememberMe] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setErrorMessage("");
    setIsLoading(true);

    try {
      const response = await login(formData, rememberMe);

      if (!response.success) {
        throw new Error(response.message || "Login failed. Please try again.");
      }

      const requestedUrl = new URL(window.location.href).searchParams.get("callbackUrl");
      const destination = requestedUrl?.startsWith("/") && !requestedUrl.startsWith("//")
        ? requestedUrl
        : "/";
      router.push(destination);
      router.refresh();
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Login failed. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <section className="min-h-[calc(100vh-80px)] bg-slate-50 px-4 py-10 sm:px-6 sm:py-14 lg:flex lg:items-center lg:py-16">
      <div className="mx-auto grid w-full max-w-5xl overflow-hidden rounded-2xl border border-slate-200 bg-white shadow-xl shadow-slate-200/60 lg:grid-cols-[0.8fr_1.2fr]">
        <div className="relative hidden min-h-155 flex-col justify-between overflow-hidden bg-emerald-700 p-10 text-white lg:flex">
          <div className="absolute -left-20 -top-20 h-64 w-64 rounded-full bg-emerald-500/30" />
          <div className="absolute -bottom-24 -right-16 h-72 w-72 rounded-full bg-emerald-400/20" />
          <Link href="/" className="relative flex items-center gap-3 text-xl font-bold">
            <span className="flex h-10 w-10 items-center justify-center rounded-xl bg-white text-emerald-700">
              <ShoppingBag className="h-5 w-5" />
            </span>
            Shopcart
          </Link>
          <div className="relative">
            <p className="mb-3 text-sm font-semibold uppercase tracking-[0.2em] text-emerald-200">
              Welcome back
            </p>
            <h1 className="text-4xl font-bold leading-tight">Sign in to continue shopping</h1>
            <p className="mt-4 max-w-sm leading-7 text-emerald-100">
              Access your orders, wishlist, and saved items in one place.
            </p>
          </div>
        </div>

        <div className="px-5 py-8 sm:px-10 sm:py-12 lg:px-14">
          <div className="mb-8 flex items-center justify-between lg:hidden">
            <Link href="/" className="flex items-center gap-2 text-lg font-bold text-slate-900">
              <span className="flex h-9 w-9 items-center justify-center rounded-lg bg-emerald-700 text-white">
                <ShoppingBag className="h-4 w-4" />
              </span>
              Shopcart
            </Link>
          </div>

          <div className="mb-8">
            <p className="mb-2 text-sm font-semibold text-emerald-700">Account</p>
            <h2 className="text-3xl font-bold text-slate-900">Login</h2>
            <p className="mt-2 text-sm text-slate-500">Enter your details to access your account.</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-5">
            {errorMessage && (
              <div role="alert" className="flex items-start gap-2 rounded-xl border border-red-200 bg-red-50 px-3.5 py-3 text-sm text-red-700">
                <AlertCircle className="mt-0.5 h-4 w-4 shrink-0" />
                <span>{errorMessage}</span>
              </div>
            )}
            <FormInput
              label="Email address"
              name="email"
              type="email"
              value={formData.email}
              placeholder="you@example.com"
              autoComplete="email"
              required
              onChange={(event) => setFormData({ ...formData, email: event.target.value })}
            />
            <FormInput
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              placeholder="Enter your password"
              autoComplete="current-password"
              required
              onChange={(event) => setFormData({ ...formData, password: event.target.value })}
            />

            <div className="flex items-center justify-between text-sm">
              <label className="flex items-center gap-2 text-slate-600">
                <input
                  type="checkbox"
                  checked={rememberMe}
                  onChange={(event) => setRememberMe(event.target.checked)}
                  className="h-4 w-4 rounded border-slate-300 accent-emerald-700"
                />
                Remember me
              </label>
              <Link href="/forgot-password" className="font-semibold text-emerald-700 hover:text-emerald-800">
                Forgot password?
              </Link>
            </div>

            <button
              type="submit"
              disabled={isLoading}
              className="flex h-12 w-full items-center justify-center gap-2 rounded-xl bg-emerald-700 text-sm font-bold text-white shadow-lg shadow-emerald-700/20 transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-200 disabled:cursor-not-allowed disabled:opacity-70"
            >
              {isLoading && <LoaderCircle className="h-5 w-5 animate-spin" />}
              {isLoading ? "Logging in..." : "Login"}
            </button>
          </form>

          <p className="mt-7 text-center text-sm text-slate-600">
            Don&apos;t have an account?{" "}
            <Link href="/signup" className="font-bold text-emerald-700 hover:text-emerald-800">
              Create account
            </Link>
          </p>
        </div>
      </div>
    </section>
  );
};

export default LoginPage;

