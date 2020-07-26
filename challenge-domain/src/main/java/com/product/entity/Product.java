package com.product.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class Product {
    private int id;
    private String name;
    private BigDecimal unitPrice;
    @Builder.Default
    private int quantity = 0;
}