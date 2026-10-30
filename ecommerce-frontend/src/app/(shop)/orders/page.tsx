import { Suspense } from "react";
import OrdersPageContent from "@/components/orders/OrdersPageContent";
import Loading from "@/components/common/Loading";

const OrdersPage = () => {
  return (
    <Suspense fallback={<Loading />}>
      <OrdersPageContent />
    </Suspense>
  );
};

export default OrdersPage;