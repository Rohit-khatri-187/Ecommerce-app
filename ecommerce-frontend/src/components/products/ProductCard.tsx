import type { Product } from "@/types/product";
import Image from "next/image";
import Link from "next/link";
import AddToCartButton from "../common/AddToCartButton";

type ProductCardProps = {
  product: Product;
};

export default function ProductCard({ product }: ProductCardProps) {
  return (
    <div className="flex flex-col overflow-hidden rounded-2xl bg-white shadow-sm border border-gray-100 transition-all hover:shadow-lg hover:-translate-y-1 group ">
      <Link href={`/products/${product.id}`} className="">
        {/* Image Container */}
        <div className="relative h-64 w-full overflow-hidden bg-gray-50">
          <Image
            src={product.imageUrl}
            alt={product.name}
            fill
            className="object-cover object-center transition-transform duration-300 group-hover:scale-105"
            sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
          />
          {product.stock <= 0 && (
            <div className="absolute top-3 right-3 rounded-full bg-red-100 px-3 py-1 text-xs font-semibold text-red-600">
              Out of Stock
            </div>
          )}
        </div>

        {/* Content Container */}
        <div className="flex flex-1 flex-col p-5">
          <div className="mb-2 flex items-center justify-between">
            <span className="text-xs font-medium uppercase tracking-wider text-gray-500">
              {product.category}
            </span>
            <span className="text-lg font-bold text-gray-900">
              ${product.price.toFixed(2)}
            </span>
          </div>

          <h2 className="mb-2 text-xl font-semibold text-gray-800 line-clamp-1">
            {product.name}
          </h2>

          <p className="mb-6 text-sm text-gray-600 line-clamp-2">
            {product.description}
          </p>
        </div>
      </Link>
      <div className="p-5">
        <AddToCartButton product={product} />
      </div>
    </div>
  );
}
