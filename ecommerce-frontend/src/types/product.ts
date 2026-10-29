export type Product = {
  id: number;
  name: string;
  description: string;
  category: string;
  price: number;
  stock: number;
  imageUrl: string;
  createdDate: string;
  updatedDate: string;
};

export type PaginatedGetAllProduct = {
  content: Product[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
};

