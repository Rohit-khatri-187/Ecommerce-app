"use client";
import React, { FC } from "react";
import Logo from "./Logo";
import { X } from "lucide-react";
import { NAV_ROUTES } from "@/constants/navRoutes";
import Link from "next/link";
import { usePathname } from "next/navigation";
import SocialMedia from "./SocialMedia";
import { useOutsideClick } from "@/hooks";

type SidebarProps = {
  isOpen: boolean;
  onClose: () => void;
};

const SideMenu: FC<SidebarProps> = ({ isOpen, onClose }) => {
  const pathname = usePathname();
  const sidebarRef = useOutsideClick<HTMLDivElement>(onClose);

  return (
    <div
      className={`fixed inset-y-0 h-screen left-0 z-50 w-2/3 bg-black/50 text-white shadow-xl ${isOpen ? "translate-x-0" : "-translate-100"} hoverEffect `}
    >
      <div
        ref={sidebarRef}
        className="min-w-72 max-w-96 bg-black h-screen p-10 border-r border-r-shop_light_green flex flex-col gap-6"
      >
        <div className="flex justify-between items-center">
          <Logo className="text-white" />
          <button
            className="cursor-pointer hover:text-shop_light_green"
            onClick={onClose}
          >
            <X className="h-6 w-6" />
          </button>
        </div>
        <div className="flex flex-col space-y-3.5 font-semibold tracking-wide">
          {NAV_ROUTES.map((item) => (
            <Link
              onClick={onClose}
              key={item.title}
              href={item.href}
              className={`hover:text-shop_light_green hoverEffect ${pathname === item.href && "text-shop_light_green"} `}
            >
              {item.title}
            </Link>
          ))}
        </div>
        <SocialMedia className="" iconClassName="" tooltipClassName="" />
      </div>
    </div>
  );
};

export default SideMenu;
