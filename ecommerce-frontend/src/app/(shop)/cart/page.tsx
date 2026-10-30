import { Suspense } from "react";
import CartPageContent from "@/components/cart/CartPageContent";
import Loading from "@/components/common/Loading";

const CartPage = () => {
  return (
    <Suspense fallback={<Loading/> }>
      <CartPageContent />
    </Suspense>
  );
};

export default CartPage;