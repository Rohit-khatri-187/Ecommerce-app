import { getProducts } from "@/services/productApi";
import ProductCard from "./ProductCard";
import { cacheLife } from "next/cache";

const ProductGrid = async () => {

  'use cache'
  cacheLife('hours')


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
};

export default ProductGrid;
