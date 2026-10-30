"use client";

import { useEffect, useState, type FormEvent, type MouseEvent } from "react";
import { useRouter } from "next/navigation";
import { LoaderCircle, MapPin, Phone, X } from "lucide-react";
import { updateProfile } from "@/services/profileApi";
import type { UpdateProfileRequest, UserProfile } from "@/types/profile";

type ProfileEditModalProps = {
  isOpen: boolean;
  profile: UserProfile | null;
  onClose: () => void;
};

const ProfileEditModal = ({ isOpen, profile, onClose }: ProfileEditModalProps) => {
  const router = useRouter();
  const [formData, setFormData] = useState<UpdateProfileRequest>({
    mobileNumber: profile?.mobileNumber ?? "",
    address: profile?.address ?? "",
  });
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (!isOpen) return;

    const handleEscape = (event: KeyboardEvent) => {
      if (event.key === "Escape" && !isLoading) onClose();
    };

    document.body.style.overflow = "hidden";
    document.addEventListener("keydown", handleEscape);

    return () => {
      document.body.style.overflow = "";
      document.removeEventListener("keydown", handleEscape);
    };
  }, [isOpen, isLoading, onClose]);

  const handleBackdropClick = (event: MouseEvent<HTMLDivElement>) => {
    if (event.target === event.currentTarget && !isLoading) onClose();
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setErrorMessage("");
    setIsLoading(true);

    try {
      const response = await updateProfile(formData);
      if (!response.success) {
        throw new Error(response.message || "Could not update your profile.");
      }
      onClose();
      router.refresh();
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Could not update your profile.");
    } finally {
      setIsLoading(false);
    }
  };

  if (!isOpen) return null;

  return (
    <div
      className="fixed inset-0 z-100 flex items-end justify-center bg-slate-950/50 p-0 backdrop-blur-sm sm:items-center sm:p-4"
      onMouseDown={handleBackdropClick}
      role="presentation"
    >
      <div role="dialog" aria-modal="true" aria-labelledby="edit-profile-title" className="w-full rounded-t-2xl bg-white shadow-2xl sm:max-w-lg sm:rounded-2xl">
        <div className="flex items-start justify-between border-b border-slate-100 px-5 py-4 sm:px-6 sm:py-5">
          <div>
            <p className="text-xs font-bold uppercase tracking-[0.16em] text-emerald-700">Account</p>
            <h2 id="edit-profile-title" className="mt-1 text-xl font-bold text-slate-950">Edit profile</h2>
          </div>
          <button type="button" onClick={onClose} disabled={isLoading} className="flex h-9 w-9 items-center justify-center rounded-lg text-slate-400 transition hover:bg-slate-100 hover:text-slate-700 disabled:opacity-60" aria-label="Close edit profile">
            <X className="h-5 w-5" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-5 px-5 py-5 sm:px-6 sm:py-6">
          {errorMessage && <div role="alert" className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">{errorMessage}</div>}

          <div className="space-y-2">
            <label htmlFor="profile-mobile" className="text-sm font-semibold text-slate-700">Phone number</label>
            <div className="relative">
              <Phone className="absolute left-3.5 top-1/2 h-5 w-5 -translate-y-1/2 text-slate-400" />
              <input id="profile-mobile" name="mobileNumber" type="tel" required value={formData.mobileNumber} onChange={(event) => setFormData((current) => ({ ...current, mobileNumber: event.target.value }))} placeholder="Enter your phone number" className="h-12 w-full rounded-xl border border-slate-200 pl-11 pr-4 text-sm text-slate-900 outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100" />
            </div>
          </div>

          <div className="space-y-2">
            <label htmlFor="profile-address" className="text-sm font-semibold text-slate-700">Address</label>
            <div className="relative">
              <MapPin className="absolute left-3.5 top-3.5 h-5 w-5 text-slate-400" />
              <textarea id="profile-address" name="address" required rows={4} value={formData.address} onChange={(event) => setFormData((current) => ({ ...current, address: event.target.value }))} placeholder="Enter your address" className="w-full resize-none rounded-xl border border-slate-200 py-3 pl-11 pr-4 text-sm text-slate-900 outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100" />
            </div>
          </div>

          <button type="submit" disabled={isLoading} className="flex h-12 w-full items-center justify-center gap-2 rounded-xl bg-emerald-700 text-sm font-bold text-white shadow-lg shadow-emerald-700/20 transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-100 disabled:cursor-not-allowed disabled:opacity-70">
            {isLoading && <LoaderCircle className="h-5 w-5 animate-spin" />}
            {isLoading ? "Saving..." : "Save changes"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default ProfileEditModal;
