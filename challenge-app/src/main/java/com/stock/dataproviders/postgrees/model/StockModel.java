package com.stock.dataproviders.postgrees.model;

import com.product.dataproviders.postgrees.model.ProductModel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    private int items;
    private BigDecimal priceStock;
    private String createdAt;
    private String updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idStock", referencedColumnName = "id")
    private List<ProductModel> products;
}