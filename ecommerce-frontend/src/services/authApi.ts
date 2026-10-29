
"use server";

import { cookies } from "next/headers";
import { API_BASE_URL, AUTH_ROUTE } from "@/constants/apiRoutes";

import type {
  LoginRequest,
  LoginResponse,
  RegisterRequest,
  RegisterResponse,
} from "@/types/auth";

export async function register(
  registerRequest: RegisterRequest,
): Promise<RegisterResponse> {
  const url = `${API_BASE_URL}${AUTH_ROUTE.register}`;

  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(registerRequest),
    cache: "no-store",
  });

  if (!response.ok) {
    const body = await response.json().catch(() => null);
    throw new Error(body?.message || "Failed to register");
  }

  return response.json();
}

export async function login(
  loginRequest: LoginRequest,
  rememberMe = false,
): Promise<LoginResponse> {
  const url = `${API_BASE_URL}${AUTH_ROUTE.login}`;

  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginRequest),
    cache: "no-store",
  });

  if (!response.ok) {
    const body = await response.json().catch(() => null);
    throw new Error(body?.message || "Failed to login");
  }

  const loginResponse: LoginResponse = await response.json();

  if (!loginResponse.success || !loginResponse.content?.token) {
    throw new Error(loginResponse.message || "Login failed. Please try again.");
  }

  const cookieStore = await cookies();
  cookieStore.set("auth-token", loginResponse.content.token, {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "lax",
    path: "/",
    ...(rememberMe ? { maxAge: 60 * 60 * 24 * 30 } : {}),
  });

  return loginResponse;
}

export async function logout(): Promise<void> {
  const cookieStore = await cookies();
  cookieStore.set("auth-token", "", {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "lax",
    path: "/",
    maxAge: 0,
  });
}

