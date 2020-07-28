package com.product.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    @Builder.Default
    private String createdAt = generatingDateTime();
    @Builder.Default
    private String updatedAt = generatingDateTime();

    public static String generatingDateTime() {
        String AMERICA_SAO_PAULO = "America/Sao_Paulo";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        return LocalDateTime.now(ZoneId.of(AMERICA_SAO_PAULO))
                .format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }


//    public static Product factoryProductByStock(Stock stock) {
//        return Product.builder()
//                .idStock(stock.getId())
//                .name("Máquina Evolution")
//                .unitPrice(BigDecimal.valueOf(748.99))
//                .quantity(ThreadLocalRandom.current().nextInt(11, 608))
//                .build();
//    }

    public static Product factory(Product product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}