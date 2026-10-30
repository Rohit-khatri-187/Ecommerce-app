export type UserProfile = {
  name: string;
  email: string;
  mobileNumber: string;
  address: string;
  createdAt: string;
  updatedAt: string;
};

export type ProfileResponse = {
  content: UserProfile;
  success: boolean;
  message: string;
};

export type UpdateProfileRequest = {
  mobileNumber: string;
  address: string;
};