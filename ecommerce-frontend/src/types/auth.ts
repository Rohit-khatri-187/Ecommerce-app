export type RegisterRequest = {
  name: string;
  email: string;
  password: string;
};

export type RegisterResponse = {
  content: {
    id: number;
    name: string;
    email: string;
    role: string; 
    createdDate: string; 
    updatedDate: string;
  };
  success: boolean;
  message: string;
};

export type LoginRequest = {
  email: string;
  password: string;
};

export type LoginResponse = {
  content: {
    token: string;
    role:  string;
  };
  success: boolean;
  message: string;
};