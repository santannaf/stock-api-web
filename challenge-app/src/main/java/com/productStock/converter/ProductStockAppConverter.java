package com.productStock.converter;

import com.productStock.dataproviders.postgrees.model.CustomProductStockModel;
import com.productStock.dataproviders.postgrees.model.ProductStockModel;
import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;
import com.productStock.entrypoint.data.request.ProductStockRequest;

public interface ProductStockAppConverter {
    ProductStock toProductStockDomainByRequest(ProductStockRequest request);
    ProductStockModel toProductStockModel(ProductStock productStock);
    ProductStock toProductStockDomain(ProductStockModel model);
    CustomProductStock toCustomProductStock(CustomProductStockModel customProductStockModel);
}