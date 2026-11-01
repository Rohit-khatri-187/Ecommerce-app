"use client";

import { useEffect, type ReactNode } from "react";
import { useThemeStore } from "@/store/themeStore";

const ThemeProvider = ({ children }: { children: ReactNode }) => {
  const theme = useThemeStore((state) => state.theme);

  useEffect(() => {
    document.documentElement.classList.toggle("dark", theme === "dark");
    document.documentElement.style.colorScheme = theme;
  }, [theme]);

  return <>{children}</>;
};

export default ThemeProvider;
