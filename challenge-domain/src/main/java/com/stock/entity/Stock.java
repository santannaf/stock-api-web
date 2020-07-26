package com.stock.entity;

import com.product.entity.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class Stock {
    private int id;
    private String name;
    @Builder.Default
    private int items = 0;
    @Builder.Default
    private BigDecimal priceStock = BigDecimal.ZERO;
    @Builder.Default
    private List<Product> products = Collections.emptyList();
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
}