"use client";

import { useState, type MouseEvent } from "react";
import { LoaderCircle } from "lucide-react";
import { useRouter } from "next/navigation";
import { addToCart } from "@/services/cartApi";
import type { Product } from "@/types/product";

type AddToCartButtonProps = {
  product: Product;
};

const AddToCartButton = ({ product }: AddToCartButtonProps) => {
  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleAddToCart = async (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    event.stopPropagation();
    setMessage("");
    setIsLoading(true);

    try {
      const response = await addToCart({ productId: product.id, quantity: 1 });

      if (!response.success) {
        throw new Error(response.message || "Could not add this item to the cart.");
      }

      setMessage("Added to cart");
      router.refresh();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Could not add this item to the cart.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="mt-auto">
      <button
        type="button"
        onClick={handleAddToCart}
        disabled={product.stock <= 0 || isLoading}
        className={`flex w-full items-center justify-center gap-2 rounded-xl py-3 text-sm font-semibold transition-colors
          ${
            product.stock > 0
              ? "bg-black text-white hover:bg-gray-800 active:bg-gray-900 disabled:cursor-not-allowed disabled:opacity-70"
              : "cursor-not-allowed bg-gray-100 text-gray-400"
          }`}
      >
        {isLoading && <LoaderCircle className="h-4 w-4 animate-spin" />}
        {isLoading ? "Adding..." : product.stock > 0 ? "Add to Cart" : "Out of Stock"}
      </button>
      {message && (
        <p
          role="status"
          className={`mt-2 text-center text-xs font-medium ${message === "Added to cart" ? "text-emerald-700" : "text-red-600"}`}
        >
          {message}
        </p>
      )}
    </div>
  );
};

export default AddToCartButton;
