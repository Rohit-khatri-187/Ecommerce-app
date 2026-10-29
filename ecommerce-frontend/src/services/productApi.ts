import { API_BASE_URL, PRODUCT_ROUTE } from "@/constants/apiRoutes";
import type { Product, PaginatedGetAllProduct } from "@/types/product";


export async function getProducts(): Promise<PaginatedGetAllProduct> {
  const url = `${API_BASE_URL}${PRODUCT_ROUTE.getProduct}`;

  const response = await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },

  });

  if (!response.ok) {
    throw new Error("Failed to fetch products");
  }

  return response.json();
}

export async function getProductById(id: number): Promise<Product> {
  const url = `${API_BASE_URL}${PRODUCT_ROUTE.getProductById(id)}`;

  const response = await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    throw new Error("Failed to fetch product");
  }

  return response.json();
}