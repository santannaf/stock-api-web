package com.product.usecase;

import com.exceptions.product.ProductResponseNotFoundException;
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

    public Product create(Product product) {
        log.info("Receive product to create - '{}'", product.getName());
        return this.productDataProvider.create(product);

//        return this.stockDataProvider.findById(product.getIdStock()).map(result -> {
//            Product factoryProduct = this.factoryProduct(product);
//            return this.productDataProvider.create(factoryProduct);
//        }).<StockResponseNotFoundException>orElseThrow(() -> {
//            throw new StockResponseNotFoundException(format("Stock not found to id '%s'", product.getIdStock()));
//        });
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


//    public void distributeProductsAtStock(List<Stock> stocks) {
//        stocks.stream().map(Product::factoryProductByStock).forEach(this.productDataProvider::create);
//    }
//
//    private Product factoryProduct(Product product) {
//        return Product.builder()
//                .name(product.getName())
//                .unitPrice(product.getUnitPrice())
//                .quantity(product.getQuantity())
//                .idStock(product.getIdStock())
//                .build();
//    }
}