package com.stock.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class StockWithClassification {
    private int id;
    private String name;
    private int items;
    private BigDecimal valueStock;
    private String classification;
}