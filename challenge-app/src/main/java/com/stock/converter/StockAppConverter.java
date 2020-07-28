package com.stock.converter;

import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.StockRequest;

public interface StockAppConverter {
    Stock toStockDomain(StockRequest request);
    StockModel toStockModel(Stock entity);
    Stock toStockDomainByModel(StockModel model);
    Stock toStockDomainByModelAfterUpdate(StockModel model);
    StockModel toStockModelWithId(Stock entity);
}