package com.stock.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class Stock {
    private int id;
    private String name;
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

    public static Stock factory(Stock stock) {
        return Stock.builder()
                .id(stock.getId())
                .name(stock.getName())
                .createdAt(stock.getCreatedAt())
                .updatedAt(stock.getUpdatedAt())
                .build();
    }

    public static Stock factoryStockInitialOnlyName(String name) {
        return Stock.builder()
                .name(format("Polo - %s", name))
                .build();
    }
}