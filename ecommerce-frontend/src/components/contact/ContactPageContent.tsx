"use client";

import Image from "next/image";
import React, { useState } from "react";
import { Clock3, Mail, MapPin, Phone, Send } from "lucide-react";
import Container from "@/components/common/Container";
import { contact_us } from "../../../public/images/index";

const ContactPageContent = () => {
  const [submitted, setSubmitted] = useState(false);

  const handleSubmit = (event: React.SubmitEvent) => {
    event.preventDefault();
    setSubmitted(true);
    (event.currentTarget as HTMLFormElement).reset();
  };

  return (
    <section className="bg-slate-50 py-10 sm:py-14 lg:py-16">
      <Container>
        <div className="overflow-hidden rounded-2xl border border-slate-200 bg-white shadow-xl shadow-slate-200/50">
          <div className="grid lg:grid-cols-[0.9fr_1.1fr]">
            <div className="relative min-h-72 overflow-hidden bg-emerald-900 sm:min-h-96 lg:min-h-155">
              <Image
                src={contact_us}
                alt="Shopcart customer support team"
                fill
                priority
                className="object-cover opacity-75"
                sizes="(max-width: 1024px) 100vw, 42vw"
              />
              <div className="absolute inset-0 bg-linear-to-t from-slate-950/85 via-slate-950/20 to-transparent" />
              <div className="absolute inset-x-0 bottom-0 p-6 text-white sm:p-9">
                <p className="text-sm font-bold uppercase tracking-[0.18em] text-emerald-200">
                  Customer care
                </p>
                <h1 className="mt-2 text-3xl font-bold sm:text-4xl">
                  We&apos;re here to help
                </h1>
                <p className="mt-3 max-w-md text-sm leading-6 text-slate-100 sm:text-base">
                  Have a question about an order, delivery, or product? Our
                  support team is ready to assist you.
                </p>
              </div>
            </div>

            <div className="px-5 py-8 sm:px-10 sm:py-10 lg:px-12">
              <div className="mb-7">
                <p className="text-sm font-bold text-emerald-700">
                  Get in touch
                </p>
                <h2 className="mt-2 text-3xl font-bold text-slate-950">
                  Send us a message
                </h2>
              </div>

              {submitted && (
                <div
                  role="status"
                  className="mb-5 rounded-xl border border-emerald-200 bg-emerald-50 px-4 py-3 text-sm font-medium text-emerald-800"
                >
                  Thank you! Your message has been received and our team will be
                  in touch soon.
                </div>
              )}

              <form onSubmit={handleSubmit} className="space-y-5">
                <div className="grid gap-5 sm:grid-cols-2">
                  <div className="space-y-2">
                    <label
                      htmlFor="contact-name"
                      className="text-sm font-semibold text-slate-700"
                    >
                      Your name
                    </label>
                    <input
                      id="contact-name"
                      name="name"
                      required
                      placeholder="John Doe"
                      className="h-12 w-full rounded-xl border border-slate-200 px-4 text-sm outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100"
                    />
                  </div>
                  <div className="space-y-2">
                    <label
                      htmlFor="contact-email"
                      className="text-sm font-semibold text-slate-700"
                    >
                      Email address
                    </label>
                    <input
                      id="contact-email"
                      name="email"
                      type="email"
                      required
                      placeholder="you@example.com"
                      className="h-12 w-full rounded-xl border border-slate-200 px-4 text-sm outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100"
                    />
                  </div>
                </div>

                <div className="space-y-2">
                  <label
                    htmlFor="contact-subject"
                    className="text-sm font-semibold text-slate-700"
                  >
                    Subject
                  </label>
                  <select
                    id="contact-subject"
                    name="subject"
                    className="h-12 w-full rounded-xl border border-slate-200 bg-white px-4 text-sm text-slate-700 outline-none transition focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100"
                  >
                    <option>Order support</option>
                    <option>Product question</option>
                    <option>Shipping and delivery</option>
                    <option>Returns and refunds</option>
                    <option>Other</option>
                  </select>
                </div>

                <div className="space-y-2">
                  <label
                    htmlFor="contact-message"
                    className="text-sm font-semibold text-slate-700"
                  >
                    Message
                  </label>
                  <textarea
                    id="contact-message"
                    name="message"
                    required
                    rows={5}
                    placeholder="How can we help you?"
                    className="w-full resize-none rounded-xl border border-slate-200 px-4 py-3 text-sm outline-none transition placeholder:text-slate-400 focus:border-emerald-600 focus:ring-4 focus:ring-emerald-100"
                  />
                </div>

                <button
                  type="submit"
                  className="flex h-12 w-full items-center justify-center gap-2 rounded-xl bg-emerald-700 text-sm font-bold text-white shadow-lg shadow-emerald-700/20 transition hover:bg-emerald-800 focus:outline-none focus:ring-4 focus:ring-emerald-200"
                >
                  <Send className="h-4 w-4" /> Send message
                </button>
              </form>

              <div className="mt-8 grid gap-3 border-t border-slate-100 pt-6 sm:grid-cols-2">
                <a
                  href="mailto:support@shopcart.com"
                  className="flex items-center gap-3 rounded-xl bg-slate-50 p-3 text-sm text-slate-700 hover:text-emerald-700"
                >
                  <Mail className="h-5 w-5 shrink-0 text-emerald-700" />
                  <span>
                    <span className="block text-xs text-slate-500">Email</span>
                    support@shopcart.com
                  </span>
                </a>
                <a
                  href="tel:+15550197642"
                  className="flex items-center gap-3 rounded-xl bg-slate-50 p-3 text-sm text-slate-700 hover:text-emerald-700"
                >
                  <Phone className="h-5 w-5 shrink-0 text-emerald-700" />
                  <span>
                    <span className="block text-xs text-slate-500">Phone</span>
                    +1 555 019 7642
                  </span>
                </a>
                <div className="flex items-center gap-3 rounded-xl bg-slate-50 p-3 text-sm text-slate-700">
                  <MapPin className="h-5 w-5 shrink-0 text-emerald-700" />
                  <span>
                    <span className="block text-xs text-slate-500">
                      Address
                    </span>
                    18 Market Street, New York
                  </span>
                </div>
                <div className="flex items-center gap-3 rounded-xl bg-slate-50 p-3 text-sm text-slate-700">
                  <Clock3 className="h-5 w-5 shrink-0 text-emerald-700" />
                  <span>
                    <span className="block text-xs text-slate-500">Hours</span>
                    Mon–Fri, 9am–6pm
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Container>
    </section>
  );
};

export default ContactPageContent;
