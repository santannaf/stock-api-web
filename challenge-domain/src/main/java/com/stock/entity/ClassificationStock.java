package com.stock.entity;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class ClassificationStock {
    private int id;
    private String description;

    public static ClassificationStock factory(Map<Integer, String> rule) {
        ClassificationStock classificationStock = ClassificationStock.builder().build();
        rule.forEach((k, v) -> {
            classificationStock.setId(k);
            classificationStock.setDescription(v);
        });
        return classificationStock;
    }
}