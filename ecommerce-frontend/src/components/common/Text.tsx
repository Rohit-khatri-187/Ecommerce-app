import { cn } from "cn";
import React from "react";

type TitleProps = {
  children: React.ReactNode;
  className?: string;
};

const Title = ({ children, className }: TitleProps) => {
  return (
    <h2
      className={cn(
        "text-2xl md:text-3xl font-bold text-shop_dark_green capitalize tracking-wide ",
        className,
      )}
    >
      {children}
    </h2>
  );
};

const SubTitle = ({ children, className }: TitleProps) => {
  return (
    <h3 className={cn("font-semibold text-gray-900 font-sans ", className)}>
      {children}
    </h3>
  );
};

const SubText = ({ children, className }: TitleProps) => {
  return <p className={cn("text-gray-600 text-sm", className)}>{children}</p>;
};

export { Title, SubText, SubTitle };
