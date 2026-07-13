export interface CustomerRequestDTO {
  fName: string;
  lName: string;
  email: string;
  password: string;
}

export interface CustomerResponseDTO {
  id: number;
  fName: string;
  lName: string;
  orders: OrderSummaryDTO[];
}

export interface CustomerSummaryDTO {
  id: number;
  fName: string;
  lName: string;
}

export interface OrderSummaryDTO {
  id: number;
  orderDate: string;
}
