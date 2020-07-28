package com.productStock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomProductStock {
    private int idStock;
    private String nameStock;
    private String nameProduct;
    private int quantity;
    private BigDecimal price;
    private BigDecimal amount;
}