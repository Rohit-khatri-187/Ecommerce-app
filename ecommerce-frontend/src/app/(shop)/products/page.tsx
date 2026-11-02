import { Suspense } from "react";
import Loading from "@/components/common/Loading";
import ProductGrid from "@/components/products/ProductGrid";



export default function ProductsPage() {
  return (
    <main className="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8">
      <header className="mb-8 md:mb-12">
        <h1 className="text-3xl font-extrabold tracking-tight text-gray-900 sm:text-4xl">
          All Products
        </h1>
        <p className="mt-2 text-lg text-gray-500">
          Discover our latest collection of premium items.
        </p>
      </header>

      <Suspense fallback={<Loading />}>
        <ProductGrid />
      </Suspense>
    </main>
  );
}
