package com.productStock.usecase;

import com.exceptions.stock.StockResponseNotFoundException;
import com.product.entity.Product;
import com.product.usecase.ProductUseCase;
import com.productStock.dataprovider.ProductStockDataProvider;
import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;
import com.stock.entity.Stock;
import com.stock.usecase.StockUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class ProductStokeUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductStockDataProvider productStockDataProvider;
    private final StockUseCase stockUseCase;
    private final ProductUseCase productUseCase;

    public ProductStock insertProductAtStock(ProductStock productStock) {
        log.info("Receive product to insert into stock product id '{}', stock id '{}'",
                productStock.getProduct().getId(),
                productStock.getStock().getId());

        int idStock = productStock.getStock().getId();
        int idProduct = productStock.getProduct().getId();

        Stock stock = this.stockUseCase.findById(idStock);
        Product product = this.productUseCase.findById(idProduct);

        productStock.setProduct(product);
        productStock.setStock(stock);
        return this.productStockDataProvider.insertProductIntoStock(productStock);
    }

    public List<ProductStock> listAllProductsInTheStocks() {
        log.info("Receive to find all products in the stocks");
        List<ProductStock> productStocks = this.productStockDataProvider.listAllProductsTheStocks();
        if (productStocks.isEmpty()) {
            throw new StockResponseNotFoundException("Don't exists stocks.");
        }
        return productStocks;
    }

    public List<ProductStock> loadStockWithProduct(int idProduct) {
        List<Stock> stocks = this.stockUseCase.listAll();
        Product product = this.productUseCase.findById(idProduct);
        return stocks.stream()
                .parallel()
                .map(s -> ProductStock.factory(s, product))
                .map(this.productStockDataProvider::insertProductIntoStock)
                .collect(Collectors.toList());
    }

    public List<CustomProductStock> listAllProductsInTheStocksCustom() {
        log.info("Receive to find all products in the stocks");
        return this.productStockDataProvider.listAllStocksCustoms();
    }
}