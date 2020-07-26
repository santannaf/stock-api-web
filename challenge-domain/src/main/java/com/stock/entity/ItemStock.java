package com.stock.entity;

import com.product.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
public class ItemStock {
    private int idStock;
    private Product product;
    private int quantity;
}