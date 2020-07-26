package com.product.usecase;

import com.product.dataprovider.ProductDataProvider;
import com.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class ProductUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductDataProvider productDataProvider;

    public Product create(Product product) {
        log.info("Receive product to create - '{}'", product.getName());
        Product factoryProduct = this.factoryProduct(product);
        return this.productDataProvider.create(factoryProduct);
    }

    public List<Product> listAll() {
        log.info("Receive list all products");
        return this.productDataProvider.listAll();
    }

    public Product factoryProductWithQuantity(Product product, int quantity) {
        return Product.builder()
                .quantity(quantity)
                .unitPrice(product.getUnitPrice())
                .name(product.getName())
                .build();
    }

    private Product factoryProduct(Product product) {
        return Product.builder()
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .build();
    }
}