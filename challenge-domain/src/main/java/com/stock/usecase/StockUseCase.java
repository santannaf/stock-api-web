package com.stock.usecase;

import com.exceptions.StockResponseNotFoundException;
import com.product.entity.Product;
import com.product.usecase.ProductUseCase;
import com.stock.dataprovider.StockDataProvider;
import com.stock.entity.ItemStock;
import com.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

@Named
@RequiredArgsConstructor
public class StockUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StockDataProvider stockDataProvider;
    private final ProductUseCase productUseCase;

    public Stock create(Stock entity) {
        log.info("Receive stock to create - '{}'", entity.getName());
        Stock factoryStock = this.factoryStock(entity);
        return this.stockDataProvider.create(factoryStock);
    }

    public List<Stock> listAll() {
        log.info("Receive to find all stock");
        List<Stock> stocks = this.stockDataProvider.findAll();

        if (stocks.isEmpty())
            throw new StockResponseNotFoundException("Don't exists stocks.");

        return stocks;
    }

    public Stock findById(int id) {
        log.info("Receive search to find stock by id - '{}'", id);
        Optional<Stock> optionalStock = this.stockDataProvider.findById(id);

        if (optionalStock.isEmpty())
            throw new StockResponseNotFoundException("Resource not found");

        return optionalStock.get();
    }

    public Stock update(ItemStock itemStock) throws StockResponseNotFoundException {
        log.info("Receive to update stock '{}'", itemStock.toString());
        AtomicInteger newQuantity = new AtomicInteger(0);
        AtomicReference<BigDecimal> newPriceStock = new AtomicReference<>(BigDecimal.ZERO);
        return this.stockDataProvider.findById(itemStock.getIdStock()).map(stockFound -> {
            List<Product> products = stockFound.getProducts();
            newPriceStock.getAndSet(stockFound.getPriceStock());
            newQuantity.getAndSet(itemStock.getQuantity());

            Optional<Product> product = products.stream()
                    .filter(p -> p.getId() == itemStock.getIdProduct())
                    .findFirst();

            if (product.isEmpty()) {
                throw new StockResponseNotFoundException(format("Product with id '%s' not found",
                                                                                            itemStock.getIdProduct()));
            } else {
                var quantityAccumulated = newQuantity.accumulateAndGet(product.get().getQuantity(), Integer::sum);
                var np = BigDecimal.valueOf(quantityAccumulated).multiply(product.get().getUnitPrice());
                newPriceStock.getAndAccumulate(np, BigDecimal::add);
                Product productUpdated = Product.builder()
                        .id(product.get().getId())
                        .name(product.get().getName())
                        .unitPrice(product.get().getUnitPrice())
                        .quantity(quantityAccumulated)
                        .idStock(itemStock.getIdStock())
                        .build();
                products.remove(product.get());
                products.add(this.productUseCase.update(productUpdated));
            }

            return Stock.builder()
                    .id(stockFound.getId())
                    .name(stockFound.getName())
                    .priceStock(newPriceStock.get().setScale(2, RoundingMode.DOWN))
                    .items(products.stream().map(Product::getQuantity).reduce(0, Integer::sum))
                    .products(products)
                    .createdAt(stockFound.getCreatedAt())
                    .updatedAt(Stock.generatingDateTime())
                    .build();
        }).flatMap(this.stockDataProvider::put)
                .orElseThrow(() -> {
                    throw new StockResponseNotFoundException("Resource not found");
                });
    }

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