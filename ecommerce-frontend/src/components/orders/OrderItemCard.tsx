"use client";

import Image from "next/image";
import { useRouter } from "next/navigation";
import { useTransition, useState } from "react";
import { LoaderCircle, Trash2 } from "lucide-react";
import { deleteOrderItem } from "@/services/orderApi";
import type { OrderItem } from "@/types/order";

const getStatusStyle = (status: string) => {
  const normalizedStatus = status.toLowerCase();

  if (normalizedStatus.includes("deliver") || normalizedStatus.includes("complete")) {
    return "bg-emerald-50 text-emerald-700 ring-emerald-600/20";
  }
  if (normalizedStatus.includes("cancel")) {
    return "bg-red-50 text-red-700 ring-red-600/20";
  }
  return "bg-amber-50 text-amber-700 ring-amber-600/20";
};

const OrderItemCard = ({ item }: { item: OrderItem }) => {
  const router = useRouter();
  const [isPending, startTransition] = useTransition();
  const [errorMessage, setErrorMessage] = useState("");

  const handleDelete = () => {
    setErrorMessage("");

    startTransition(async () => {
      try {
        const response = await deleteOrderItem(item.orderItemId);
        if (!response.success) {
          throw new Error(response.message || "Could not remove this order item.");
        }
        router.refresh();
      } catch (error) {
        setErrorMessage(error instanceof Error ? error.message : "Could not remove this order item.");
      }
    });
  };

  return (
    <article className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm sm:p-5">
      <div className="flex gap-4 sm:items-center sm:gap-5">
        <div className="relative h-24 w-24 shrink-0 overflow-hidden rounded-xl bg-slate-100 sm:h-28 sm:w-28">
          {item.imageUrl ? (
            <Image
              src={item.imageUrl}
              alt={item.productName}
              fill
              unoptimized
              className="object-cover"
              sizes="112px"
            />
          ) : (
            <div className="flex h-full items-center justify-center text-xs text-slate-400">No image</div>
          )}
        </div>

        <div className="min-w-0 flex-1">
          <div className="flex items-start justify-between gap-3">
            <div className="min-w-0">
              <span className={`inline-flex rounded-full px-2.5 py-1 text-xs font-bold ring-1 ring-inset ${getStatusStyle(item.orderItemStatus)}`}>
                {item.orderItemStatus || "Pending"}
              </span>
              <h2 className="mt-3 line-clamp-2 text-base font-bold text-slate-950 sm:text-lg">
                {item.productName}
              </h2>
            </div>
            <button
              type="button"
              onClick={handleDelete}
              disabled={isPending}
              className="flex h-9 w-9 shrink-0 items-center justify-center rounded-lg text-slate-400 transition hover:bg-red-50 hover:text-red-600 disabled:cursor-not-allowed disabled:opacity-60"
              aria-label={`Remove ${item.productName} from order`}
            >
              {isPending ? (
                <LoaderCircle className="h-5 w-5 animate-spin" />
              ) : (
                <Trash2 className="h-5 w-5" />
              )}
            </button>
          </div>

          <div className="mt-4 grid grid-cols-2 gap-3 border-t border-slate-100 pt-4 text-sm sm:grid-cols-3">
            <div>
              <p className="text-xs text-slate-400">Quantity</p>
              <p className="mt-1 font-semibold text-slate-800">{item.quantity}</p>
            </div>
            <div>
              <p className="text-xs text-slate-400">Unit price</p>
              <p className="mt-1 font-semibold text-slate-800">Rs{item.unitPrice.toLocaleString("en-IN", { maximumFractionDigits: 2 })}</p>
            </div>
            <div className="col-span-2 sm:col-span-1 sm:text-right">
              <p className="text-xs text-slate-400">Item total</p>
              <p className="mt-1 text-base font-bold text-slate-950">Rs{item.totalAmount.toLocaleString("en-IN", { maximumFractionDigits: 2 })}</p>
            </div>
          </div>
        </div>
      </div>

      {errorMessage && (
        <p role="alert" className="mt-3 rounded-lg bg-red-50 px-3 py-2 text-sm text-red-700">
          {errorMessage}
        </p>
      )}
    </article>
  );
};

export default OrderItemCard;
