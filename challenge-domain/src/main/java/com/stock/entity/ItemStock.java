package com.stock.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
public class ItemStock {
    private int idProduct;
    private int quantity;
    private int idStock;
}