import { Suspense } from "react";
import { getProducts } from "@/services/productApi";
import ProductCard from "@/components/products/ProductCard";
import Loading from "@/components/common/Loading";

async function ProductGrid() {
  const data = await getProducts();

  if (!data?.content || data.content.length === 0) {
    return (
      <div className="flex min-h-[50vh] items-center justify-center rounded-2xl bg-gray-50 border border-gray-100 text-gray-500">
        No products found.
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      {data.content.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
}

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
