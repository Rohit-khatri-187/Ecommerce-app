import React from "react";
import Link from "next/link";
import {
  FaYoutube,
  FaGithub,
  FaLinkedin,
  FaXTwitter,
  FaFacebook,
} from "react-icons/fa6";
import { cn } from "cn";

const socialLinks = [
  {
    title: "YouTube",
    href: "https://www.youtube.com",
    icon: FaYoutube,
    hoverColor: "hover:bg-red-600 hover:border-red-600",
  },
  {
    title: "GitHub",
    href: "https://github.com",
    icon: FaGithub,
    hoverColor: "hover:bg-gray-800 hover:border-gray-800",
  },
  {
    title: "LinkedIn",
    href: "https://www.linkedin.com",
    icon: FaLinkedin,
    hoverColor: "hover:bg-blue-600 hover:border-blue-600",
  },
  {
    title: "X",
    href: "https://x.com",
    icon: FaXTwitter,
    hoverColor: "hover:bg-black hover:border-black",
  },
  {
    title: "Facebook",
    href: "https://www.facebook.com",
    icon: FaFacebook,
    hoverColor: "hover:bg-blue-700 hover:border-blue-700",
  },
];

type SocialLinksProps = {
  className: string;
  iconClassName: string;
  tooltipClassName: string;
};

const SocialMedia = ({
  className,
  iconClassName,
  tooltipClassName,
}: SocialLinksProps) => {
  return (
    <div className={cn("flex items-center gap-3", className)}>
      {socialLinks.map((item) => {
        const Icon = item.icon;

        return (
          <Link
            key={item.title}
            href={item.href}
            target="_blank"
            rel="noopener noreferrer"
            title={item.title}
            aria-label={item.title}
            className={cn(
              "group relative inline-flex h-10 w-10 items-center justify-center",
              "rounded-full border border-gray-300 bg-white text-gray-600",
              "shadow-sm transition-all duration-300 ease-in-out",
              "hover:-translate-y-1 hover:text-white hover:shadow-md",
              "focus-visible:outline-none focus-visible:ring-2",
              "focus-visible:ring-offset-2 focus-visible:ring-gray-500",
              item.hoverColor,
              iconClassName,
            )}
          >
            <Icon className="h-4 w-4 transition-transform duration-300 group-hover:scale-110" />

            {/* Custom tooltip using CSS */}
            <span
              className={cn(
                "pointer-events-none absolute -top-10 left-1/2 z-10",
                "-translate-x-1/2 translate-y-1 opacity-0",
                "whitespace-nowrap rounded-md bg-gray-900 px-2.5 py-1.5",
                "text-xs font-medium text-white shadow-lg",
                "transition-all duration-200",
                "group-hover:translate-y-0 group-hover:opacity-100",
                "group-focus-visible:translate-y-0 group-focus-visible:opacity-100",
                tooltipClassName,
              )}
              aria-hidden="true"
            >
              {item.title}
            </span>
          </Link>
        );
      })}
    </div>
  );
};

export default SocialMedia;
