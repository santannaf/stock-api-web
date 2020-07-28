package com.productStock.dataproviders.postgrees.model;

import com.product.dataproviders.postgrees.model.ProductModel;
import com.stock.dataproviders.postgrees.model.StockModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product_stock")
public class ProductStockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idProduct")
    private ProductModel product;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idStock")
    private StockModel stock;
    private String createdAt;
    private String updatedAt;
}