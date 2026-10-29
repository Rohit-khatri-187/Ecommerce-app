import React from "react";
import Container from "./Container";
import FooterTop from "./FooterTop";
import Logo from "./Logo";
import SocialMedia from "./SocialMedia";
import { SubText, SubTitle } from "./Text";
import { categoriesData, quickLinksData } from "@/constants/navRoutes";
import Link from "next/link";



const Footer = () => {
  return (
    <footer className="bg-white border-t">
      <Container>
        <FooterTop />
        <div className="py-12 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          <div className="flex flex-col space-y-4">
            <Logo />
            <SubText>
              Discover curated furniture collections at Shopcartyt , blending
              style and comfort to elevate your living spaces
            </SubText>
            <SocialMedia className="" iconClassName="" tooltipClassName="" />
          </div>
          <div>
            <SubTitle>QuickLinks</SubTitle>
            <ul className="space-y-3 mt-4">
              {quickLinksData.map((item, index) => (
                <li key={index}>
                  <Link
                    className="hover:text-shop_light_green hoverEffect font-medium"
                    href={item.href}
                  >
                    {" "}
                    {item.title}{" "}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
          <div>
            <SubTitle>Categories</SubTitle>
            <ul className="space-y-3 mt-4">
              {categoriesData.map((item, index) => (
                <li key={index}>
                  <Link
                    className="hover:text-shop_light_green hoverEffect font-medium"
                    href={`/category${item.href}`}
                  >
                    {" "}
                    {item.title}{" "}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
          <div className="space-y-4" >
            <SubTitle>Newsletter</SubTitle>
            <SubText>
              Subscribe to our newsletter to receive updates and exclusive offers
            </SubText>
            <form className="space-y-3" >
              <input placeholder="Enter your email" type="email" required />
              <button className="w-full" >Subscribe</button>
            </form>
          </div>
        </div>
      </Container>
    </footer>
  );
};

export default Footer;
