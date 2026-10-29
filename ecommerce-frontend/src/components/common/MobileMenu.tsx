"use client";
import { AlignLeft } from "lucide-react";
import React, { Suspense, useState } from "react";
import SideMenu from "./SideMenu";

const MobileMenu = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  return (
    <div>
      <button onClick={() => setIsSidebarOpen(!isSidebarOpen)}>
        <AlignLeft className="hover:text-darkColor hoverEffect md:hidden hover:cursor-pointer " />
      </button>
      <div className="md:hidden">
        <Suspense>
          <SideMenu
            isOpen={isSidebarOpen}
            onClose={() => setIsSidebarOpen(false)}
          />
        </Suspense>
      </div>
    </div>
  );
};

export default MobileMenu;
