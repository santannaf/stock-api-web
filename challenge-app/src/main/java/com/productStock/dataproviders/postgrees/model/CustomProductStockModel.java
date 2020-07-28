package com.productStock.dataproviders.postgrees.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomProductStockModel {
    private int idStock;
    private String nameStock;
    private String nameProduct;
    private int quantity;
    private BigDecimal price;
}