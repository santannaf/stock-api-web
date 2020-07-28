package com.productStock.converter.impl;

import com.product.converter.ProductAppConverter;
import com.product.entity.Product;
import com.productStock.converter.ProductStockAppConverter;
import com.productStock.dataproviders.postgrees.model.CustomProductStockModel;
import com.productStock.dataproviders.postgrees.model.ProductStockModel;
import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;
import com.productStock.entrypoint.data.request.ProductStockRequest;
import com.stock.converter.StockAppConverter;
import com.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductStockAppConverterImpl implements ProductStockAppConverter {
    private final StockAppConverter stockAppConverter;
    private final ProductAppConverter productAppConverter;

    @Override
    public ProductStock toProductStockDomainByRequest(ProductStockRequest request) {
        return ProductStock.builder()
                .quantity(request.getQuantity())
                .product(Product.builder().id(request.getIdProduct()).build())
                .stock(Stock.builder().id(request.getIdStock()).build())
                .build();
    }

    @Override
    public ProductStockModel toProductStockModel(ProductStock entity) {
        return ProductStockModel.builder()
                .quantity(entity.getQuantity())
                .product(this.productAppConverter.toProductModelWithId(entity.getProduct()))
                .stock(this.stockAppConverter.toStockModelWithId(entity.getStock()))
                .build();
    }

    @Override
    public ProductStock toProductStockDomain(ProductStockModel model) {
        return ProductStock.builder()
                .id(model.getId())
                .quantity(model.getQuantity())
                .product(this.productAppConverter.toProductDomainByModel(model.getProduct()))
                .stock(this.stockAppConverter.toStockDomainByModel(model.getStock()))
                .build();
    }

    @Override
    public CustomProductStock toCustomProductStock(CustomProductStockModel customProductStockModel) {
        return CustomProductStock.builder()
                .idProduct(customProductStockModel.getIdProduct())
                .idStock(customProductStockModel.getIdStock())
                .quantity(customProductStockModel.getQuantity())
                .build();
    }
}