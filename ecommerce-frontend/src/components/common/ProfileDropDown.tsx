"use client";

import { useEffect, useRef, useState } from "react";
import { useRouter } from "next/navigation";
import { CircleUserRound, LoaderCircle, LogOut, Pencil } from "lucide-react";
import { logout } from "@/services/authApi";
import ProfileEditModal from "./ProfileEditModal";
import type { UserProfile } from "@/types/profile";
import { useCartStore } from "@/store/cartStore";

const ProfileDropDown = ({ profile }: { profile: UserProfile | null }) => {
  const router = useRouter();
  const menuRef = useRef<HTMLDivElement>(null);
  const [isOpen, setIsOpen] = useState(false);
  const [isEditOpen, setIsEditOpen] = useState(false);
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const resetCart = useCartStore((state) => state.resetCart);

  useEffect(() => {
    const closeMenu = (event: MouseEvent) => {
      if (!menuRef.current?.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    const closeWithEscape = (event: KeyboardEvent) => {
      if (event.key === "Escape") {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", closeMenu);
    document.addEventListener("keydown", closeWithEscape);

    return () => {
      document.removeEventListener("mousedown", closeMenu);
      document.removeEventListener("keydown", closeWithEscape);
    };
  }, []);

  const handleLogout = async () => {
    setIsLoggingOut(true);

    try {
      await logout();
      resetCart();
      setIsOpen(false);
      router.push("/");
      router.refresh();
    } finally {
      setIsLoggingOut(false);
    }
  };

  return (
    <>
      <div ref={menuRef} className="relative">
        <button
          type="button"
          onClick={() => setIsOpen((current) => !current)}
          className="flex h-9 w-9 items-center justify-center rounded-full bg-emerald-700 text-white transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-100"
          aria-label="Open profile menu"
          aria-expanded={isOpen}
        >
          <CircleUserRound className="h-5 w-5" aria-hidden="true" />
        </button>

        {isOpen && (
          <div className="absolute right-0 top-full z-50 mt-3 w-64 overflow-hidden rounded-xl border border-slate-200 bg-white shadow-xl shadow-slate-300/40">
            <div className="border-b border-slate-100 px-4 py-3.5">
              <p className="truncate text-sm font-bold text-slate-900">
                {profile?.name || "Account"}
              </p>
              <p className="mt-1 truncate text-xs text-slate-500">
                {profile?.email || ""}
              </p>
            </div>
            <div className="space-y-1 p-2">
              <button
                type="button"
                onClick={() => {
                  setIsOpen(false);
                  setIsEditOpen(true);
                }}
                className="flex w-full items-center gap-2 rounded-lg px-3 py-2.5 text-sm font-semibold text-slate-700 transition hover:bg-slate-100"
              >
                <Pencil className="h-4 w-4" />
                Edit profile
              </button>
              <button
                type="button"
                onClick={handleLogout}
                disabled={isLoggingOut}
                className="flex w-full items-center gap-2 rounded-lg px-3 py-2.5 text-sm font-semibold text-red-600 transition hover:bg-red-50 disabled:cursor-not-allowed disabled:opacity-60"
              >
                {isLoggingOut ? (
                  <LoaderCircle className="h-4 w-4 animate-spin" />
                ) : (
                  <LogOut className="h-4 w-4" />
                )}
                {isLoggingOut ? "Logging out..." : "Logout"}
              </button>
            </div>
          </div>
        )}
      </div>

      {isEditOpen && (
        <ProfileEditModal
          isOpen={isEditOpen}
          profile={profile}
          onClose={() => setIsEditOpen(false)}
        />
      )}
    </>
  );
};

export default ProfileDropDown;
