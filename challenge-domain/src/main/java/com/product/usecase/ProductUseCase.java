package com.product.usecase;

import com.exceptions.StockResponseNotFoundException;
import com.product.dataprovider.ProductDataProvider;
import com.product.entity.Product;
import com.stock.dataprovider.StockDataProvider;
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
    private final StockDataProvider stockDataProvider;

    public Product create(Product product) {
        log.info("Receive product to create - '{}'", product.getName());
        return this.stockDataProvider.findById(product.getIdStock()).map(result -> {
            Product factoryProduct = this.factoryProduct(product);
            return this.productDataProvider.create(factoryProduct);
        }).<StockResponseNotFoundException>orElseThrow(() -> {
            throw new StockResponseNotFoundException(format("Stock not found to id '%s'", product.getIdStock()));
        });
    }

    public List<Product> listAll() {
        log.info("Receive list all products");
        return this.productDataProvider.listAll();
    }

    public Product update(Product product) {
        log.info("Receive product to update - '{}'", product.getName());
        return this.productDataProvider.put(product).<StockResponseNotFoundException>orElseThrow(() -> {
            throw new StockResponseNotFoundException(format("Product not found to id '%s'", product.getId()));
        });
    }

    private Product factoryProduct(Product product) {
        return Product.builder()
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .idStock(product.getIdStock())
                .build();
    }
}