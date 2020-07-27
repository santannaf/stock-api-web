package com.stock.entrypoint.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductToUpdateStockRequest {
    private int id;
    private String name;
    private BigDecimal unitPrice;
}