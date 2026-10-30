import { Suspense, type ReactNode } from "react";
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";

export default function ShopLayout({ children }: { children: ReactNode }) {
  return (
    <div className="min-h-screen flex flex-col">
      <Suspense fallback={<div className="h-21 bg-white" />}>
        <Header />
      </Suspense>
      <main className="flex-1 min-h-screen bg-slate-50">{children}</main>
      <Footer />
    </div>
  );
}
