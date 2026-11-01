"use client";

import { useEffect } from "react";
import { ShoppingBag } from "lucide-react";
import Link from "next/link";
import { selectCartItemCount, useCartStore } from "@/store/cartStore";

const CartIcon = () => {
  const itemCount = useCartStore(selectCartItemCount);
  const refreshCart = useCartStore((state) => state.refreshCart);

  useEffect(() => {
    refreshCart();
  }, [refreshCart]);

  return (
    <Link
      href="/cart"
      className="group relative inline-flex items-center justify-center"
      aria-label={`Shopping cart with ${itemCount} items`}
    >
      <ShoppingBag className="h-6 w-6 transition-colors group-hover:text-emerald-700" />
      <span
        className={`absolute -right-3 -top-3 flex h-5 min-w-5 items-center justify-center rounded-full border-2 border-white px-1 text-[10px] font-bold leading-none shadow-md dark:border-slate-900 ${
          itemCount > 0
            ? "bg-emerald-700 text-white"
            : "bg-slate-200 text-slate-700"
        }`}
      >
        {itemCount > 99 ? "99+" : itemCount}
      </span>
    </Link>
  );
};

export default CartIcon;
