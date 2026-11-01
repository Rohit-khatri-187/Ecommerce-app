import Container from "./Container";
import Logo from "./Logo";
import HeaderMenu from "./HeaderMenu";
import SearchBar from "./SearchBar";
import CartIcon from "./CartIcon";
import MobileMenu from "./MobileMenu";
import { Suspense } from "react";
import { cookies } from "next/headers";
import Link from "next/link";
import ProfileDropDown from "./ProfileDropDown";
import { getProfile } from "@/services/profileApi";
import type { UserProfile } from "@/types/profile";
import ThemeToggle from "./ThemeToggle";

const Header = async () => {
  const cookieStore = await cookies();
  const isLoggedIn = Boolean(cookieStore.get("auth-token")?.value);
  let profile: UserProfile | null = null;

  if (isLoggedIn) {
    try {
      const profileResponse = await getProfile();
      if (profileResponse.success) profile = profileResponse.content;
    } catch {
      profile = null;
    }
  }

  return (
    <header className="bg-white py-5">
      <Container className="flex items-center justify-between">
        <div className="w-auto md:w-1/3 flex items-center gap-2.5 justify-start text-lightColor md:gap-0">
          <MobileMenu />
          <Logo />
        </div>

        <Suspense>
          <HeaderMenu />
        </Suspense>

        <div className="w-auto md:w-13 flex items-center justify-end gap-5">
          <SearchBar />
          <CartIcon />
          <ThemeToggle />
          {isLoggedIn ? (
            <ProfileDropDown profile={profile} />
          ) : (
            <Link
              href="/login"
              className="rounded-lg bg-emerald-700 px-3 py-2 text-sm font-semibold text-white transition hover:bg-emerald-800 sm:px-4"
            >
              Login
            </Link>
          )}
        </div>
      </Container>
    </header>
  );
};

export default Header;
