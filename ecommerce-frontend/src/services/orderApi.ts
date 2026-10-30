"use server";

import { getAuthHeaders } from "@/config/authHeaders";
import { API_BASE_URL, ORDER_ROUTE } from "@/constants/apiRoutes";
import type {
  DeleteOrderResponse,
  GetOrderResponse,
  PlaceOrderRequest,
  PlaceOrderResponse,
} from "@/types/order";

async function getErrorMessage(response: Response, fallback: string) {
  const body = await response.json().catch(() => null);
  return body?.message || fallback;
}

export async function getAllOrders(): Promise<GetOrderResponse> {
  const response = await fetch(`${API_BASE_URL}${ORDER_ROUTE.getAllOrders}`, {
    method: "GET",
    headers: await getAuthHeaders(),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to fetch orders"));
  }

  return response.json();
}

export async function placeOrder(
  placeOrderRequest: PlaceOrderRequest,
): Promise<PlaceOrderResponse> {
  const response = await fetch(`${API_BASE_URL}${ORDER_ROUTE.placeOrder}`, {
    method: "POST",
    headers: await getAuthHeaders(),
    body: JSON.stringify(placeOrderRequest),
    cache: "no-store",
  });

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to place order"));
  }

  return response.json();
}

export async function deleteOrderItem(
  orderItemId: number,
): Promise<DeleteOrderResponse> {
  const response = await fetch(
    `${API_BASE_URL}${ORDER_ROUTE.deleteOrderItemById(orderItemId)}`,
    {
      method: "DELETE",
      headers: await getAuthHeaders(),
      cache: "no-store",
    },
  );

  if (!response.ok) {
    throw new Error(await getErrorMessage(response, "Failed to delete order item"));
  }

  return response.json();
}
