import Image from "next/image";
import Link from "next/link";
import { ArrowRight, CalendarDays, Clock3 } from "lucide-react";
import Container from "@/components/common/Container";
import { banner_1, blog } from "../../../public/images/index";

const articles = [
  {
    category: "Buying Guide",
    title: "How to Choose Furniture That Fits Your Home and Lifestyle",
    excerpt: "A practical guide to measuring your space, choosing durable materials, and finding furniture that feels right for everyday living.",
    date: "October 24, 2026",
    readTime: "6 min read",
    image: banner_1,
  },
  {
    category: "Home Styling",
    title: "Small-Space Styling Ideas for a Calm, Modern Home",
    excerpt: "Discover simple storage solutions, color ideas, and furniture layouts that make compact rooms feel brighter and more spacious.",
    date: "October 18, 2026",
    readTime: "4 min read",
    image: blog,
  },
  {
    category: "Product Care",
    title: "Easy Ways to Keep Your Furniture Looking New for Longer",
    excerpt: "Learn which everyday cleaning habits and protective products help preserve wood, fabric, and metal finishes over time.",
    date: "October 9, 2026",
    readTime: "5 min read",
    image: banner_1,
  },
];

const BlogPageContent = () => {
  return (
    <>
      <section className="border-b border-slate-100 bg-slate-50 py-12 sm:py-16">
        <Container>
          <div className="grid items-center gap-8 lg:grid-cols-[1fr_0.75fr]">
            <div>
              <p className="mb-3 text-sm font-bold uppercase tracking-[0.18em] text-emerald-700">
                The Shopcart Journal
              </p>
              <h1 className="max-w-3xl text-4xl font-bold leading-tight text-slate-950 sm:text-5xl">
                Ideas for a more comfortable home
              </h1>
              <p className="mt-5 max-w-2xl text-base leading-7 text-slate-600 sm:text-lg">
                Buying guides, styling inspiration, and practical advice to help you choose products you will love for years.
              </p>
            </div>
            <div className="relative h-56 overflow-hidden rounded-2xl shadow-xl shadow-slate-200 sm:h-72 lg:h-64">
              <Image src={blog} alt="A modern and inviting living room" fill className="object-cover" priority sizes="(max-width: 1024px) 100vw, 40vw" />
            </div>
          </div>
        </Container>
      </section>

      <section className="py-12 sm:py-16">
        <Container>
          <div className="mb-8 flex items-end justify-between gap-4">
            <div>
              <p className="text-sm font-bold text-emerald-700">Latest stories</p>
              <h2 className="mt-2 text-3xl font-bold text-slate-950">From our journal</h2>
            </div>
          </div>

          <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
            {articles.map((article) => (
              <article key={article.title} className="group overflow-hidden rounded-2xl border border-slate-200 bg-white transition hover:-translate-y-1 hover:shadow-xl hover:shadow-slate-200/70">
                <div className="relative h-52 overflow-hidden">
                  <Image src={article.image} alt={article.title} fill className="object-cover transition duration-500 group-hover:scale-105" sizes="(max-width: 768px) 100vw, (max-width: 1024px) 50vw, 33vw" />
                  <span className="absolute left-4 top-4 rounded-full bg-white/95 px-3 py-1.5 text-xs font-bold text-emerald-700 shadow-sm">
                    {article.category}
                  </span>
                </div>
                <div className="p-5 sm:p-6">
                  <div className="flex flex-wrap items-center gap-4 text-xs font-medium text-slate-500">
                    <span className="flex items-center gap-1.5"><CalendarDays className="h-4 w-4" />{article.date}</span>
                    <span className="flex items-center gap-1.5"><Clock3 className="h-4 w-4" />{article.readTime}</span>
                  </div>
                  <h3 className="mt-4 text-xl font-bold leading-7 text-slate-950">{article.title}</h3>
                  <p className="mt-3 text-sm leading-6 text-slate-600">{article.excerpt}</p>
                  <Link href="#" className="mt-5 inline-flex items-center gap-2 text-sm font-bold text-emerald-700 hover:text-emerald-800">
                    Read article <ArrowRight className="h-4 w-4 transition group-hover:translate-x-1" />
                  </Link>
                </div>
              </article>
            ))}
          </div>
        </Container>
      </section>
    </>
  );
};

export default BlogPageContent;
