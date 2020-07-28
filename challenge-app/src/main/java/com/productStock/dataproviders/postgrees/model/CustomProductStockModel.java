package com.productStock.dataproviders.postgrees.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomProductStockModel {
    private int idStock;
    private int idProduct;
    private int quantity;
}