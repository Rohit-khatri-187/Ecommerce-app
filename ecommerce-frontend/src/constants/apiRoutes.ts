export const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

export const AUTH_ROUTE = {
  register: "/auth/register",
  login: "/auth/login",
};

export const PRODUCT_ROUTE = {
  getProduct: "/products",
  getProductById: (id: number) => `/products/${id}`,
};

export const CART_ROUTE = {
  getAllCarts: "/carts",
  addToCart: "/carts",
  deleteCartItemById: (id: number) => `/carts/${id}`,
};

export const PROFILE_ROUTE = {
  getProfile: "/profile",
  updateProfile: "/profile",
};

export const ORDER_ROUTE = {
  getAllOrders: "/orders",
  placeOrder: "/orders",
  deleteOrderItemById: (id: number) => `/orders/${id}`,
};

export const PUBLIC_ROUTES = [
  "/",
  "/login",
  "/signup",
  "/products",
  "/blog",
];

export const AUTH_ROUTES = ["/login", "/signup"];

export const PRIVATE_ROUTES = [
  "/cart",
  "/profile",
  "/orders",
  "/checkout",
];
