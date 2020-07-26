package com.product.dataproviders.postgrees.model;

import com.stock.dataproviders.postgrees.model.StockModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal unitPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "idStock")
    private StockModel stockModel;
}