export type ApiResponse<T> = {
  content: T;
  success: boolean;
  message: string;
};

export type CartItem = {
  id: number;
  name: string;
  quantity: number;
  totalAmount: number;
  imageUrl: string;
  createdDate: string; 
  updatedDate: string; 
};

export type AddToCartRequest = {
  productId: number;
  quantity: number;
};

export type DeleteCartItemResponse = ApiResponse<null>;
export type GetCartItemResponse = ApiResponse<CartItem[]>;
export type AddToCartResponse = ApiResponse<CartItem>;