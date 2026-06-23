package com.northernarc.customerproductspringdatajpa.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {

    private Long order_id;

    private LocalDate orderDate;

    private CustomerSummaryDTO customerSummaryDTO;

    private List<OrderItemSummaryDTO> orderItems;
}
