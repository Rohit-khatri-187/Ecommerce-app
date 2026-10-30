"use server";

import { API_BASE_URL, PROFILE_ROUTE } from "@/constants/apiRoutes";
import { getAuthHeaders } from "@/config/authHeaders";
import type {
  ProfileResponse,
  UpdateProfileRequest,
} from "@/types/profile";

async function getErrorMessage(response: Response, fallback: string) {
  const body = await response.json().catch(() => null);
  return body?.message || fallback;
}

export async function getProfile(): Promise<ProfileResponse> {
  const response = await fetch(`${API_BASE_URL}${PROFILE_ROUTE.getProfile}`, {
    method: "GET",
    headers: await getAuthHeaders(),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to fetch profile"));
  }

  return response.json();
}

export async function updateProfile(
  updateProfileRequest: UpdateProfileRequest,
): Promise<ProfileResponse> {
  const response = await fetch(`${API_BASE_URL}${PROFILE_ROUTE.updateProfile}`, {
    method: "POST",
    headers: await getAuthHeaders(),
    body: JSON.stringify(updateProfileRequest),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to update profile"));
  }

  return response.json();
}
