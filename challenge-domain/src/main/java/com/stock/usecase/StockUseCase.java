package com.stock.usecase;

import com.exceptions.product.ProductResponseNotFoundException;
import com.exceptions.stock.StockResponseNotFoundException;
import com.stock.dataprovider.StockDataProvider;
import com.stock.dataprovider.StockInitialPolesDataProvider;
import com.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

@Named
@RequiredArgsConstructor
public class StockUseCase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StockDataProvider stockDataProvider;
    private final StockInitialPolesDataProvider stockInitialPolesDataProvider;

    @PostConstruct
    void createInitialStock() {
        this.stockInitialPolesDataProvider.getPoles().stream()
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
    }

    public void delete(int id) {
        log.info("Receive to delete stock by id - '{}'", id);
        this.stockDataProvider.delete(id);
    }

    public Integer stockConsumer() {
        return ThreadLocalRandom.current().nextInt(1, 44);
    }
}