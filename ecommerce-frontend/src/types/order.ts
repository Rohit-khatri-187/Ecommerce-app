

export type ApiResponse<T> = {
  content: T;
  success: boolean;
  message: string;
};

export type OrderItem = {
  orderItemId: number;
  productId: number;
  productName: string;
  imageUrl: string;
  quantity: number;
  unitPrice: number;
  totalAmount: number;
  orderItemStatus: string;
};



export type PlaceOrderRequest = {
  address: string;
  phoneNumber: string;
};

export type GetOrderResponse = ApiResponse<OrderItem[]>;

export type PlaceOrderResponse = ApiResponse<OrderItem[]>;

export type DeleteOrderResponse = ApiResponse<null>;