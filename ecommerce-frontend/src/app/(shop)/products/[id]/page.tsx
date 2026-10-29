import { Suspense } from "react";

import { notFound } from "next/navigation";
import { ProductDetailView } from "@/components/products/ProductDetailView";
import Loading from "@/components/common/Loading";

export const instant = false;

type ProductPageProps = {
  params: Promise<{ id: string }>;
};

export default async function ProductDetailsPage({ params }: ProductPageProps) {
  const { id } = await params;
  const productId = Number(id);

  if (Number.isNaN(productId)) {
    notFound();
  }

  return (
    <main className="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8 lg:py-16">
      <Suspense fallback={<Loading />}>
        <ProductDetailView id={productId} />
      </Suspense>
    </main>
  );
  
}
