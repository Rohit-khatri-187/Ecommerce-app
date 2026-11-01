import { create } from "zustand";
import {
  addToCart as addCartItemApi,
  deleteCartItem as deleteCartItemApi,
  getAllProducts,
} from "@/services/cartApi";
import type {
  AddToCartRequest,
  AddToCartResponse,
  CartItem,
  DeleteCartItemResponse,
} from "@/types/cart";

type CartStore = {
  items: CartItem[];
  isLoading: boolean;
  errorMessage: string;
  hasLoaded: boolean;
  setItems: (items: CartItem[]) => void;
  resetCart: () => void;
  refreshCart: () => Promise<void>;
  addCartItem: (request: AddToCartRequest) => Promise<AddToCartResponse>;
  removeCartItem: (cartItemId: number) => Promise<DeleteCartItemResponse>;
};

export const useCartStore = create<CartStore>((set, get) => ({
  items: [],
  isLoading: false,
  errorMessage: "",
  hasLoaded: false,

  setItems: (items) => set({ items, hasLoaded: true, errorMessage: "" }),
  resetCart: () => set({ items: [], isLoading: false, errorMessage: "", hasLoaded: false }),

  refreshCart: async () => {
    if (get().isLoading) return;

    set({ isLoading: true, errorMessage: "" });

    try {
      const response = await getAllProducts();
      if (!response.success) {
        throw new Error(response.message || "Unable to load your cart.");
      }
      set({
        items: response.content ?? [],
        hasLoaded: true,
        isLoading: false,
        errorMessage: "",
      });
    } catch (error) {
      set({
        isLoading: false,
        hasLoaded: true,
        errorMessage:
          error instanceof Error ? error.message : "Unable to load your cart.",
      });
    }
  },

  addCartItem: async (request) => {
    const response = await addCartItemApi(request);
    if (response.success) {
      await get().refreshCart();
    }
    return response;
  },

  removeCartItem: async (cartItemId) => {
    const response = await deleteCartItemApi(cartItemId);
    if (response.success) {
      set((state) => ({
        items: state.items.filter((item) => item.id !== cartItemId),
      }));
    }
    return response;
  },
}));

export const selectCartItemCount = (state: CartStore) =>
  state.items.reduce((total, item) => total + item.quantity, 0);
