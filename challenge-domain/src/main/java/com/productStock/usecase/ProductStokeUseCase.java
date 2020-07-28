package com.productStock.usecase;

import com.exceptions.stock.StockResponseNotFoundException;
import com.product.entity.Product;
import com.product.usecase.ProductUseCase;
import com.productStock.dataprovider.ProductStockDataProvider;
import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;
import com.stock.entity.ClassificationStock;
import com.stock.entity.Stock;
import com.stock.entity.StockWithClassification;
import com.stock.usecase.CriticismCoverageUseCase;
import com.stock.usecase.StockUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class ProductStokeUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductStockDataProvider productStockDataProvider;
    private final StockUseCase stockUseCase;
    private final ProductUseCase productUseCase;
    private final CriticismCoverageUseCase criticismCoverageUseCase;

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

    public StockWithClassification listProductsInTheStockCustomByIdStock(int idStock) {
        log.info("Receive to find all products in the stocks");
        Stock stock = this.stockUseCase.findById(idStock);
        List<CustomProductStock> customProductStocks = this.productStockDataProvider.listStocksCustomsByIdStock(idStock);

        if (customProductStocks.isEmpty())
            throw new StockResponseNotFoundException("There are no products inserted in this stock.");

        BigDecimal totalAmount = this.totalAmountIntoStock(customProductStocks, stock.getName());
        int totalQuantity = this.totalQuantityIntoStock(customProductStocks, stock.getName());
        int stockConsumerDynamic = this.stockUseCase.stockConsumer();
        Map<Integer, String> rule =
                this.criticismCoverageUseCase.calculateCoverage(stockConsumerDynamic, totalQuantity);

        return StockWithClassification.builder()
                .id(stock.getId())
                .name(stock.getName())
                .items(totalQuantity)
                .valueStock(totalAmount)
                .classification(ClassificationStock.factory(rule))
                .build();
    }

    public List<StockWithClassification> listAllProductsInTheStocksCustom() {
        log.info("Receive to find all products in the stocks");
        List<StockWithClassification> stocksWithClassifications = new ArrayList<>();
        List<CustomProductStock> customProductStocks = this.productStockDataProvider.listAllStocksCustoms();

        Map<Integer, String> stocks = customProductStocks.stream()
                .distinct()
                .collect(Collectors.toMap(CustomProductStock::getIdStock, CustomProductStock::getNameStock, (name1, name2) -> name1));

        stocks.forEach((k, v) -> {
            BigDecimal totalAmount = this.totalAmountIntoStock(customProductStocks, v);
            int totalQuantity = this.totalQuantityIntoStock(customProductStocks, v);
            int stockConsumerDynamic = this.stockUseCase.stockConsumer();
            Map<Integer, String> rule = this.criticismCoverageUseCase.calculateCoverage(stockConsumerDynamic, totalQuantity);
            stocksWithClassifications.add(StockWithClassification.builder()
                    .id(k)
                    .name(v)
                    .items(totalQuantity)
                    .valueStock(totalAmount)
                    .classification(ClassificationStock.factory(rule))
                    .build());
        });
        return stocksWithClassifications;
    }

    private BigDecimal totalAmountIntoStock(List<CustomProductStock> customProductStocks, String nameStock) {
        return customProductStocks.stream()
                .collect(Collectors.groupingBy(CustomProductStock::getNameStock,
                        Collectors.reducing(BigDecimal.ZERO, CustomProductStock::getAmount, BigDecimal::add)))
                .get(nameStock);
    }

    private int totalQuantityIntoStock(List<CustomProductStock> customProductStocks, String nameStock) {
        return customProductStocks.stream()
                .collect(Collectors.groupingBy(CustomProductStock::getNameStock,
                        Collectors.reducing(0, CustomProductStock::getQuantity, Integer::sum)))
                .get(nameStock);
    }
}