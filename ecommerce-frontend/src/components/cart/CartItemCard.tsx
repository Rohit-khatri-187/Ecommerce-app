"use client";

import { useTransition } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { LoaderCircle, Trash2 } from "lucide-react";
import { deleteCartItem } from "@/services/cartApi";
import type { CartItem } from "@/types/cart";

type CartItemCardProps = {
  item: CartItem;
};

const CartItemCard = ({ item }: CartItemCardProps) => {
  const router = useRouter();
  const [isPending, startTransition] = useTransition();

  const handleRemove = () => {
    startTransition(async () => {
      await deleteCartItem(item.id);
      router.refresh();
    });
  };

  return (
    <article className="flex gap-4 rounded-2xl border border-slate-200 bg-white p-4 shadow-sm sm:items-center sm:gap-5 sm:p-5">
      <div className="relative h-24 w-24 shrink-0 overflow-hidden rounded-xl bg-slate-100 sm:h-28 sm:w-28">
        {item.imageUrl ? (
          <Image
            src={item.imageUrl}
            alt={item.name}
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
            <h3 className="line-clamp-2 text-base font-bold text-slate-950 sm:text-lg">{item.name}</h3>
            <p className="mt-1 text-sm text-slate-500">Quantity: {item.quantity}</p>
          </div>
          <button
            type="button"
            onClick={handleRemove}
            disabled={isPending}
            className="flex h-9 w-9 shrink-0 items-center justify-center rounded-lg text-slate-400 transition hover:bg-red-50 hover:text-red-600 disabled:cursor-not-allowed disabled:opacity-60"
            aria-label={`Remove ${item.name} from cart`}
          >
            {isPending ? (
              <LoaderCircle className="h-5 w-5 animate-spin" />
            ) : (
              <Trash2 className="h-5 w-5" />
            )}
          </button>
        </div>

        <div className="mt-5 flex items-center justify-between border-t border-slate-100 pt-4 sm:mt-4">
          <span className="text-xs font-medium uppercase tracking-wide text-slate-400">Item total</span>
          <span className="text-lg font-bold text-slate-950">Rs{item.totalAmount.toLocaleString("en-IN", { maximumFractionDigits: 2 })}</span>
        </div>
      </div>
    </article>
  );
};

export default CartItemCard;
