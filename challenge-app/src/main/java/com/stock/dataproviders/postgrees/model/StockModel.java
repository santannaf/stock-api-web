package com.stock.dataproviders.postgrees.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stock")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String createdAt;
    private String updatedAt;
}