import { Suspense } from "react";
import Container from "@/components/common/Container";
import HomeBanner from "@/components/common/HomeBanner";
import ProductGrid from "@/components/products/ProductGrid";
import Loading from "@/components/common/Loading";

const HomePage = () => {
  return (
    <Container>
      <HomeBanner />
      <div className="mt-5 mb-5">
        <Suspense fallback={<Loading />}>
          <ProductGrid />
        </Suspense>
      </div>
    </Container>
  );
};

export default HomePage;
