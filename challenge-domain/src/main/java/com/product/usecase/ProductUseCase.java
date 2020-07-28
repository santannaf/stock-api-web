package com.product.usecase;

import com.exceptions.product.ProductResponseNotFoundException;
import com.product.dataprovider.ProductDataProvider;
import com.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.List;

import static java.lang.String.format;

@Named
@RequiredArgsConstructor
public class ProductUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductDataProvider productDataProvider;

    public Product create(Product product) {
        log.info("Receive product to create - '{}'", product.getName());
        return this.productDataProvider.create(product);
    }

    public Product findById(int idProduct) {
        log.info("Receive find product by id '{}'", idProduct);
        return this.productDataProvider.findById(idProduct)
                .<ProductResponseNotFoundException>orElseThrow(() -> {
            throw new ProductResponseNotFoundException(format("Product not found to id '%s'", idProduct));
        });
    }

    public List<Product> listAll() {
        log.info("Receive list all products");
        return this.productDataProvider.listAll();
    }

    public Product update(Product product) {
        log.info("Receive product to update - '{}'", product.getName());
        return this.productDataProvider.put(product).<ProductResponseNotFoundException>orElseThrow(() -> {
            throw new ProductResponseNotFoundException(format("Product not found to id '%s'", product.getId()));
        });
    }

    public void deleteById(int idProduct) {
        log.info("Receive delete product by id '{}'", idProduct);
        this.productDataProvider.delete(idProduct);
    }
}