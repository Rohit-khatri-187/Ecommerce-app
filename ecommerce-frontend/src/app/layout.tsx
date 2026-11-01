import "./globals.css";
import ThemeProvider from "@/components/common/ThemeProvider";

export default function RootLayout({ children }: LayoutProps<"/">) {
  return (
    <html lang="en" className="h-full antialiased" suppressHydrationWarning>
      <head>
        <script
          dangerouslySetInnerHTML={{
            __html:
              'try{const saved=localStorage.getItem("theme-storage");const theme=saved?JSON.parse(saved)?.state?.theme:null;if(theme==="dark")document.documentElement.classList.add("dark") }catch(e){}',
          }}
        />
      </head>
      <body className="min-h-full">
        <ThemeProvider>{children}</ThemeProvider>
      </body>
    </html>
  );
}
