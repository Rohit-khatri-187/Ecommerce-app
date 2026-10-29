import { getProductById } from "@/services/productApi";
import Image from "next/image";
import AddToCartButton from "../common/AddToCartButton";

export async function ProductDetailView({ id }: { id: number }) {
  const product = await getProductById(id);

  return (
    <div className="flex flex-col lg:flex-row gap-10 lg:gap-16">
      {/* Left Side: Image */}
      <div className="w-full lg:w-1/2">
        <div className="relative aspect-square w-full overflow-hidden rounded-3xl bg-gray-50 shadow-sm border border-gray-100">
          <Image
            src={product.imageUrl}
            alt={product.name}
            fill
            className="object-cover object-center"
            sizes="(max-width: 1024px) 100vw, 50vw"
            priority
            loading="eager"
          />
        </div>
      </div>

      {/* Right Side: Details */}
      <div className="w-full lg:w-1/2 flex flex-col justify-center">
        <div className="mb-2">
          <span className="inline-flex items-center rounded-full bg-blue-50 px-3 py-1 text-xs font-medium text-blue-700 ring-1 ring-inset ring-blue-700/10 uppercase tracking-wider">
            {product.category}
          </span>
        </div>

        <h1 className="text-3xl sm:text-4xl lg:text-5xl font-extrabold text-gray-900 tracking-tight mb-4">
          {product.name}
        </h1>

        <p className="text-3xl font-bold text-gray-900 mb-6">
          ${product.price.toFixed(2)}
        </p>

        <div className="prose prose-gray mb-8">
          <p className="text-base sm:text-lg text-gray-600 leading-relaxed">
            {product.description}
          </p>
        </div>

        <div className="mt-auto border-t border-gray-100 pt-8">
          <div className="flex items-center justify-between mb-6">
            <span className="text-sm font-medium text-gray-700">
              Availability
            </span>
            {product.stock > 0 ? (
              <span className="text-sm font-semibold text-green-600">
                {product.stock} in stock
              </span>
            ) : (
              <span className="text-sm font-semibold text-red-600">
                Out of stock
              </span>
            )}
          </div>

          <AddToCartButton product={product} />
        </div>
      </div>
    </div>
  );
}
