package com.stock.converter;

import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.entity.ItemStock;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.ItemStockRequest;
import com.stock.entrypoint.data.request.StockRequest;

public interface StockAppConverter {
    Stock toStockDomain(StockRequest request);
    StockModel toStockModel(Stock entity);
    Stock toStockDomainByModel(StockModel model);
    ItemStock toItemStock(ItemStockRequest itemStockRequest);
}