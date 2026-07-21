export interface LoanProductRequest {
  loanCode: string;
  loanName: string;
  loanType: string;
  interestRate: number;
  dailyPenaltyRate: number;
}

export interface LoanProductResponse {
  loanCode: string;
  loanName: string;
  loanType: string;
  interestRate: number;
  dailyPenaltyRate: number;
}

export interface LoanAccountRequest {
  loanStartDate: string;
  emiDueDate: string;
  loanAmount: number;
  emiAmount: number;
  loanCode: string;
  customerId?: number;
}

export interface LoanAccountResponse {
  loanAccountId: number;
  loanStartDate: string;
  emiDueDate: string;
  loanCloseDate: string | null;
  loanStatus: string;
  loanAmount: number;
  emiAmount: number;
  customerId: number;
  customerName: string;
  customerEmail: string;
  loanCode: string;
  loanType: string;
}

export interface EmiPaymentRequest {
  amountPaid: number;
  penaltyPaid: number;
  paymentType: string;
  paymentDate: string;
  loanAccountId: number;
}

export interface EmiPaymentResponse {
  paymentId: number;
  amountPaid: number;
  penaltyPaid: number;
  paymentType: string;
  paymentDate: string;
  loanAccountId: number;
}

export interface DashboardResponse {
  totalCustomers: number;
  totalLoans: number;
  totalLoanAmountDisbursed: number;
  totalPenaltyCollected: number;
  topBranch: string;
  highestLoanCustomer: string;
}

export interface ApiMessage {
  message: string;
}

export interface ErrorResponse {
  status: string;
  message: string;
  timestamp: string;
  errors: string[];
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
