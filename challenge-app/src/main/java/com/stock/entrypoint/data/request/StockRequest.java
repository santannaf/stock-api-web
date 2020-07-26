package com.stock.entrypoint.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockRequest {
    @NotNull(message = "Name cannot null.")
    @NotEmpty(message = "Name cannot empty.")
    @NotBlank(message = "Name cannot blank.")
    private String name;
}