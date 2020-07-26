package com.product.dataprovider;

import com.product.entity.Product;

import java.util.List;

public interface ProductDataProvider {
    Product create(Product product);
    List<Product> listAll();
}