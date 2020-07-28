package com.stock.usecase;

import com.exceptions.product.ProductResponseNotFoundException;
import com.exceptions.stock.StockResponseNotFoundException;
import com.stock.dataprovider.StockDataProvider;
import com.stock.dataprovider.StockInitialDataProvider;
import com.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.List;

import static java.lang.String.format;

@Named
@RequiredArgsConstructor
public class StockUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StockDataProvider stockDataProvider;
    private final StockInitialDataProvider stockInitialDataProvider;


//    private final CriticismCoverageDataProvider criticismCoverageDataProvider;

    @PostConstruct
    void createInitialStock() {
        this.stockInitialDataProvider.getPoles().stream()
                .map(Stock::factoryStockInitialOnlyName)
                .forEach(stockDataProvider::create);
    }

    public Stock create(Stock entity) {
        log.info("Receive stock to create - '{}'", entity.getName());
        return this.stockDataProvider.create(entity);
    }

    public List<Stock> listAll() {
        log.info("Receive to find all stock");
        List<Stock> stocks = this.stockDataProvider.findAll();

        if (stocks.isEmpty()) {
            throw new StockResponseNotFoundException("Don't exists stocks.");
        }

        return stocks;
    }

    public Stock findById(int id) {
        log.info("Receive search to find stock by id - '{}'", id);
        return this.stockDataProvider.findById(id).<StockResponseNotFoundException>orElseThrow(() -> {
            throw new StockResponseNotFoundException("Resource not found");
        });
    }

    public Stock update(Stock stock) {
        log.info("Receive stock to update - '{}'", stock.getName());
        return this.stockDataProvider.put(stock).<ProductResponseNotFoundException>orElseThrow(() -> {
            throw new ProductResponseNotFoundException(format("Stock not found to id '%s'", stock.getId()));
        });

//        log.info("Receive to update stock '{}'", itemStock.toString());
//        AtomicInteger newQuantity = new AtomicInteger(0);
//        AtomicReference<BigDecimal> newPriceStock = new AtomicReference<>(BigDecimal.ZERO);
//        return this.stockDataProvider.findById(itemStock.getIdStock()).map(stockFound -> {
//            List<Product> products = stockFound.getProducts();
//            newPriceStock.getAndSet(stockFound.getPriceStock());
//            newQuantity.getAndSet(itemStock.getQuantity());
//
//            Optional<Product> product = products.stream()
//                    .filter(p -> p.getId() == itemStock.getIdProduct())
//                    .findFirst();
//
//            if (!product.isPresent()) {
//                throw new StockResponseNotFoundException(format("Product with id '%s' not found", itemStock.getIdProduct()));
//            } else {
//                int quantityAccumulated = newQuantity.accumulateAndGet(product.get().getQuantity(), Integer::sum);
//                BigDecimal np = BigDecimal.valueOf(quantityAccumulated).multiply(product.get().getUnitPrice());
//                newPriceStock.getAndAccumulate(np, BigDecimal::add);
//                Product productUpdated = Product.builder()
//                        .id(product.get().getId())
//                        .name(product.get().getName())
//                        .unitPrice(product.get().getUnitPrice())
//                        .quantity(quantityAccumulated)
//                        .idStock(itemStock.getIdStock())
//                        .build();
//                products.remove(product.get());
//                products.add(this.productUseCase.update(productUpdated));
//            }
//
//            return Stock.builder()
//                    .id(stockFound.getId())
//                    .name(stockFound.getName())
//                    .priceStock(newPriceStock.get().setScale(2, RoundingMode.DOWN))
//                    .items(products.stream().map(Product::getQuantity).reduce(0, Integer::sum))
//                    .products(products)
//                    .createdAt(stockFound.getCreatedAt())
//                    .updatedAt(Stock.generatingDateTime())
//                    .build();
//        }).flatMap(this.stockDataProvider::put)
//          .map(this::factoryStockResponse)
//                .<StockResponseNotFoundException>orElseThrow(() -> {
//                    throw new StockResponseNotFoundException("Resource not found");
//                });
    }

//    private String classificationStock(Stock stock) {
//        int quantity = stock.getItems();
//        int consumer = Stock.stockConsumer();
//        log.info("Consumer stock id '{}' name '{}' is: '{}'", stock.getId(), stock.getName(), consumer);
//        return this.criticismCoverageDataProvider.calculateCoverage(consumer, quantity);
//    }
//
//    private Stock factoryStockResponse (Stock stock) {
//        return Stock.builder()
//                .id(stock.getId())
//                .name(stock.getName())
//                .priceStock(stock.getPriceStock())
//                .items(stock.getItems())
//                .classification(this.classificationStock(stock))
//                .products(stock.getProducts())
//                .createdAt(stock.getCreatedAt())
//                .updatedAt(stock.getUpdatedAt())
//                .build();
//    }

    public void delete(int id) {
        log.info("Receive to delete stock by id - '{}'", id);
        this.stockDataProvider.delete(id);
    }

    private Stock factoryStock(Stock stockInitial) {
        return Stock.builder()
                .name(stockInitial.getName())
                .build();
    }
}