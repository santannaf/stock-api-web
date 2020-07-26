package com.stock.dataproviders.postgrees.model;

import com.product.dataproviders.postgrees.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stock")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int items;
    private BigDecimal priceStock;
    private String createdAt;
    private String updatedAt;
    @OneToMany(mappedBy = "stockModel")
    private List<ProductModel> products;
}