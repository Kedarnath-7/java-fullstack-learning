export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
}

export interface CustomerRequest {
  customerName: string;
  email: string;
  password: string;
  branch: string;
  role: string;
}

export interface CustomerResponse {
  customerId: number;
  customerName: string;
  email: string;
  branch: string;
  role: string;
}

export interface CustomerSummary {
  customerName: string;
  branch: string;
  numberOfLoans: number;
  totalLoanAmount: number;
  totalPenaltyPaid: number;
}
