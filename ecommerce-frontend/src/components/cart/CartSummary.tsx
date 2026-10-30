import Link from "next/link";
import { ArrowRight, ShieldCheck } from "lucide-react";
import type { CartItem } from "@/types/cart";

const CartSummary = ({ items }: { items: CartItem[] }) => {
  const subtotal = items.reduce((total, item) => total + item.totalAmount, 0);

  return (
    <aside className="h-fit rounded-2xl border border-slate-200 bg-white p-5 shadow-sm sm:p-6 lg:sticky lg:top-6">
      <h2 className="text-xl font-bold text-slate-950">Order summary</h2>
      <div className="mt-5 space-y-3 border-b border-slate-100 pb-5 text-sm">
        <div className="flex justify-between gap-4 text-slate-600">
          <span>Subtotal</span>
          <span className="font-semibold text-slate-900">Rs{subtotal.toLocaleString("en-IN", { maximumFractionDigits: 2 })}</span>
        </div>
        <div className="flex justify-between gap-4 text-slate-600">
          <span>Shipping</span>
          <span className="font-semibold text-emerald-700">Calculated at checkout</span>
        </div>
      </div>
      <div className="flex items-center justify-between py-5">
        <span className="font-bold text-slate-950">Total</span>
        <span className="text-2xl font-bold text-slate-950">Rs{subtotal.toLocaleString("en-IN", { maximumFractionDigits: 2 })}</span>
      </div>
      <Link
        href="/checkout"
        className="flex h-12 w-full items-center justify-center gap-2 rounded-xl bg-emerald-700 text-sm font-bold text-white shadow-lg shadow-emerald-700/20 transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-100"
      >
        Continue to checkout
        <ArrowRight className="h-4 w-4" />
      </Link>
      <p className="mt-4 flex items-center justify-center gap-2 text-xs text-slate-500">
        <ShieldCheck className="h-4 w-4 text-emerald-700" /> Secure checkout
      </p>
    </aside>
  );
};

export default CartSummary;
