package com.stock.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class ProductToUpdateStock {
    private int id;
    private BigDecimal unitPrice;
    private String name;
}