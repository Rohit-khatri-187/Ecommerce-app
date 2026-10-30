import Container from "@/components/common/Container";
import NoOrders from "@/components/orders/NoOrders";
import OrderItemCard from "@/components/orders/OrderItemCard";
import { getAllOrders } from "@/services/orderApi";
import type { OrderItem } from "@/types/order";

const OrdersPageContent = async () => {
  let items: OrderItem[] = [];
  let errorMessage = "";

  try {
    const orders = await getAllOrders();
    items = orders.success ? orders.content ?? [] : [];
    if (!orders.success) errorMessage = orders.message || "Unable to load your orders.";
  } catch (error) {
    errorMessage = error instanceof Error ? error.message : "Unable to load your orders.";
  }

  return (
    <section className="bg-slate-50 py-10 sm:py-14">
      <Container>
        <div className="mb-7">
          <p className="text-sm font-bold text-emerald-700">Purchase history</p>
          <h1 className="mt-2 text-3xl font-bold text-slate-950 sm:text-4xl">My orders</h1>
        </div>

        {errorMessage ? (
          <div className="rounded-2xl border border-red-200 bg-white px-6 py-10 text-center">
            <h2 className="text-2xl font-bold text-slate-950">Unable to load your orders</h2>
            <p className="mt-2 text-sm text-slate-600">{errorMessage}</p>
          </div>
        ) : items.length === 0 ? (
          <NoOrders />
        ) : (
          <div className="mx-auto max-w-4xl space-y-4">
            <div className="flex items-center justify-between rounded-xl bg-white px-4 py-3 text-sm text-slate-500 ring-1 ring-slate-200 sm:px-5">
              <span>{items.length} {items.length === 1 ? "item" : "items"}</span>
              <span className="font-semibold text-slate-700">
                Total Rs{items.reduce((total, item) => total + item.totalAmount, 0).toLocaleString("en-IN", { maximumFractionDigits: 2 })}
              </span>
            </div>
            {items.map((item) => (
              <OrderItemCard key={item.orderItemId} item={item} />
            ))}
          </div>
        )}
      </Container>
    </section>
  );
};

export default OrdersPageContent;
