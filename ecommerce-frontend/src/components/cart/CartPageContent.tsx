import Container from "@/components/common/Container";
import CartItemCard from "@/components/cart/CartItemCard";
import CartSummary from "@/components/cart/CartSummary";
import NoCartItems from "@/components/cart/NoCartItems";
import { getAllProducts } from "@/services/cartApi";
import type { CartItem } from "@/types/cart";

const CartPageContent = async () => {
  let items: CartItem[] = [];
  let errorMessage = "";

  try {
    const cart = await getAllProducts();
    items = cart.success ? cart.content ?? [] : [];
    if (!cart.success) errorMessage = cart.message || "Unable to load your cart.";
  } catch (error) {
    errorMessage = error instanceof Error ? error.message : "Unable to load your cart.";
  }

  return (
    <section className="bg-slate-50 py-10 sm:py-14">
      <Container>
        <div className="mb-7">
          <p className="text-sm font-bold text-emerald-700">Your basket</p>
          <h1 className="mt-2 text-3xl font-bold text-slate-950 sm:text-4xl">Shopping cart</h1>
        </div>

        {errorMessage ? (
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
