"use client";

import { useEffect } from "react";
import Container from "@/components/common/Container";
import CartItemCard from "@/components/cart/CartItemCard";
import CartSummary from "@/components/cart/CartSummary";
import NoCartItems from "@/components/cart/NoCartItems";
import { useCartStore } from "@/store/cartStore";

const CartPageContent = () => {
  const items = useCartStore((state) => state.items);
  const hasLoaded = useCartStore((state) => state.hasLoaded);
  const isLoading = useCartStore((state) => state.isLoading);
  const errorMessage = useCartStore((state) => state.errorMessage);
  const refreshCart = useCartStore((state) => state.refreshCart);

  useEffect(() => {
    refreshCart();
  }, [refreshCart]);

  return (
    <section className="bg-slate-50 py-10 sm:py-14">
      <Container>
        <div className="mb-7">
          <p className="text-sm font-bold text-emerald-700">Your basket</p>
          <h1 className="mt-2 text-3xl font-bold text-slate-950 sm:text-4xl">Shopping cart</h1>
        </div>

        {isLoading && !hasLoaded ? (
          <div className="rounded-2xl border border-slate-200 bg-white px-6 py-10 text-center text-sm text-slate-500">
            Loading cart...
          </div>
        ) : errorMessage ? (
          <div className="rounded-2xl border border-red-200 bg-white px-6 py-10 text-center">
            <h2 className="text-2xl font-bold text-slate-950">Unable to load your cart</h2>
            <p className="mt-2 text-sm text-slate-600">{errorMessage}</p>
          </div>
        ) : items.length === 0 ? (
          <NoCartItems />
        ) : (
          <div className="grid gap-6 lg:grid-cols-[minmax(0,1fr)_340px]">
            <div className="space-y-4">
              {items.map((item) => (
                <CartItemCard key={item.id} item={item} />
              ))}
            </div>
            <CartSummary items={items} />
          </div>
        )}
      </Container>
    </section>
  );
};

export default CartPageContent;
