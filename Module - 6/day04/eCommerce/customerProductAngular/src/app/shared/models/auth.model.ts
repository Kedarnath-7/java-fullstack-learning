export interface AuthLoginRequest {
  email: string;
  password: string;
}

export interface AuthRegisterRequest {
  fName: string;
  lName: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  customerId: number;
  email: string;
  role: string;
}
