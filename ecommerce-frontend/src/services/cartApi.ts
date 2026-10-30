"use server";

import { cookies } from "next/headers";
import { API_BASE_URL, CART_ROUTE } from "@/constants/apiRoutes";
import type {
  AddToCartRequest,
  AddToCartResponse,
  DeleteCartItemResponse,
  GetCartItemResponse,
} from "@/types/cart";

async function getAuthHeaders(): Promise<Record<string, string>> {
  const cookieStore = await cookies();
  const token = cookieStore.get("auth-token")?.value;

  if (!token) {
    throw new Error("You must be logged in to access the cart.");
  }

  return {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  };
}

async function getErrorMessage(response: Response, fallback: string) {
  const body = await response.json().catch(() => null);
  return body?.message || fallback;
}

export async function getAllProducts(): Promise<GetCartItemResponse> {
  const response = await fetch(`${API_BASE_URL}${CART_ROUTE.getAllCarts}`, {
    method: "GET",
    headers: await getAuthHeaders(),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to fetch cart items"));
  }

  return response.json();
}

export async function getAllCartItems(): Promise<GetCartItemResponse> {
  return getAllProducts();
}

export async function addToCart(
  addToCartRequest: AddToCartRequest,
): Promise<AddToCartResponse> {
  const response = await fetch(`${API_BASE_URL}${CART_ROUTE.addToCart}`, {
    method: "POST",
    headers: await getAuthHeaders(),
    body: JSON.stringify(addToCartRequest),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to add item to cart"));
  }

  return response.json();
}

export async function deleteCartItem(
  cartItemId: number,
): Promise<DeleteCartItemResponse> {
  const response = await fetch(
    `${API_BASE_URL}${CART_ROUTE.deleteCartItemById(cartItemId)}`,
    {
      method: "DELETE",
      headers: await getAuthHeaders(),
      cache: "no-store",
    },
  );

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to remove cart item"));
  }

  return response.json();
}
