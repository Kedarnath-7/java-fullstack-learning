package com.northernarc.loanmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Generic API message response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMessageDTO {
    private String message;
}
