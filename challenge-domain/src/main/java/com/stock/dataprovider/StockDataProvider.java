package com.stock.dataprovider;

import com.stock.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockDataProvider {
    Stock create(Stock stock);
    Optional<Stock> findById(int id);
    List<Stock> findAll();
    Optional<Stock> put(Stock stock);
    void delete(int id);
}