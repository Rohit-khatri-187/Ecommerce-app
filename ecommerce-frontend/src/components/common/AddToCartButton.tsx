"use client";
import { Product } from "@/types/product";
import React from "react";

type ProductCardProps = {
  product: Product;
};

const AddToCartButton = ({ product }: ProductCardProps) => {
  const handleAddToCart = (e: React.MouseEvent) => {
    e.preventDefault();
    console.log("Add to cart:", product.id);
  };

  return (
    <button
      onClick={handleAddToCart}
      disabled={product.stock <= 0}
      className={`mt-auto w-full rounded-xl py-3 text-sm font-semibold transition-colors
            ${
              product.stock > 0
                ? "bg-black text-white hover:bg-gray-800 active:bg-gray-900"
                : "cursor-not-allowed bg-gray-100 text-gray-400"
            }
          `}
    >
      {product.stock > 0 ? "Add to Cart" : "Out of Stock"}
    </button>
  );
};

export default AddToCartButton;
