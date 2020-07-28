package com.productStock.dataprovider;

import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;

import java.util.List;

public interface ProductStockDataProvider {
    ProductStock insertProductIntoStock(ProductStock productStock);
    List<ProductStock> listAllProductsTheStocks();
    List<CustomProductStock> listStocksCustomsByIdStock(int idStock);
    List<CustomProductStock> listAllStocksCustoms();
}