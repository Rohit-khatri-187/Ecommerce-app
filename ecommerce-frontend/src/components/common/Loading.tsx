import { Loader2 } from "lucide-react";

export default function Loading() {
  return (
    <div className="absolute inset-0 flex items-center justify-center bg-white/80 dark:bg-zinc-950/80 backdrop-blur-xs z-50">
      <Loader2 className="w-8 h-8 animate-spin text-blue-500" />
    </div>
  );
}