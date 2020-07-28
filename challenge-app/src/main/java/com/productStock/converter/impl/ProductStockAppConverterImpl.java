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

import java.math.BigDecimal;
import java.math.RoundingMode;

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
                .createdAt(this.productAppConverter.now())
                .updatedAt(this.productAppConverter.now())
                .build();
    }

    @Override
    public ProductStock toProductStockDomain(ProductStockModel model) {
        return ProductStock.builder()
                .id(model.getId())
                .quantity(model.getQuantity())
                .product(this.productAppConverter.toProductDomainByModel(model.getProduct()))
                .stock(this.stockAppConverter.toStockDomainByModel(model.getStock()))
                .createdAt(this.productAppConverter.now())
                .updatedAt(this.productAppConverter.now())
                .build();
    }

    @Override
    public CustomProductStock toCustomProductStock(CustomProductStockModel customProductStockModel) {
        return CustomProductStock.builder()
                .idStock(customProductStockModel.getIdStock())
                .nameProduct(customProductStockModel.getNameProduct())
                .nameStock(customProductStockModel.getNameStock())
                .price(customProductStockModel.getPrice())
                .quantity(customProductStockModel.getQuantity())
                .amount(customProductStockModel.getPrice()
                        .multiply(BigDecimal.valueOf(customProductStockModel.getQuantity()))
                        .setScale(2, RoundingMode.DOWN))
                .build();
    }
}