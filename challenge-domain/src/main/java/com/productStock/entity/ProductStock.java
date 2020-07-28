package com.productStock.entity;

import com.product.entity.Product;
import com.stock.entity.Stock;
import lombok.*;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class ProductStock {
    private int id;
    @Builder.Default
    private int quantity = 0;
    private Stock stock;
    private Product product;

    public static ProductStock factory(Stock stock, Product product) {
        return ProductStock.builder()
                .stock(stock)
                .product(product)
                .quantity(ThreadLocalRandom.current().nextInt(11, 608))
                .build();
    }
}