import { cn } from "cn";
import Link from "next/link";
import React from "react";

const Logo = ({ className }: { className?: string }) => {
  return (
    <Link href={"/"}>
      <h2
        className={cn(
          "text-2xl text-shop_dark_green font-black hover:text-shop_light_green hoverEffect group font-sans ",
          className,
        )}
      >
        SHOPCAR
        <span className="text-shop_light_green group-hover:text-shop_dark_green hoverEffect">
          T
        </span>
      </h2>
    </Link>
  );
};

export default Logo;