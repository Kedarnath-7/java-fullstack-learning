import { CustomerSummaryDTO } from './customer.model';

export interface OrderItemRequestDTO {
  productId: number;
  quantity: number;
}

export interface OrderRequestDTO {
  orderDate: string;
  customerId: number;
  orderItems: OrderItemRequestDTO[];
}

export interface OrderItemSummaryDTO {
  id: number;
  quantity: number;
}

export interface OrderResponseDTO {
  order_id: number;
  orderDate: string;
  customerSummaryDTO: CustomerSummaryDTO;
  orderItems: OrderItemSummaryDTO[];
}
