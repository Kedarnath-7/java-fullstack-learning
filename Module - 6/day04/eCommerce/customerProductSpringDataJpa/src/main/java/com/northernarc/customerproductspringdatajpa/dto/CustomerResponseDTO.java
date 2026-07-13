package com.northernarc.customerproductspringdatajpa.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;
    private String fName;
    private String lName;

    private List<OrderSummaryDTO> orders;
}
