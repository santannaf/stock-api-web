package com.product.dataprovider;

import com.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDataProvider {
    Product create(Product product);
    List<Product> listAll();
    Optional<Product> findById(int id);
    Optional<Product> put(Product stock);
    void delete(int id);
}