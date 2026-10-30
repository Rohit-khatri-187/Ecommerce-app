import Link from "next/link";
import { ArrowRight, PackageOpen } from "lucide-react";

const NoOrders = () => {
  return (
    <div className="flex min-h-105 flex-col items-center justify-center rounded-2xl border border-dashed border-slate-300 bg-white px-6 text-center">
      <div className="flex h-16 w-16 items-center justify-center rounded-full bg-emerald-50 text-emerald-700">
        <PackageOpen className="h-8 w-8" />
      </div>
      <h2 className="mt-5 text-2xl font-bold text-slate-950">No orders yet</h2>
      <p className="mt-2 max-w-sm text-sm leading-6 text-slate-500">
        You have not placed any orders. Explore our products to find something you love.
      </p>
      <Link
        href="/products"
        className="mt-6 flex h-12 items-center gap-2 rounded-xl bg-emerald-700 px-5 text-sm font-bold text-white shadow-lg shadow-emerald-700/20 transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-100"
      >
        Browse products
        <ArrowRight className="h-4 w-4" />
      </Link>
    </div>
  );
};

export default NoOrders;
