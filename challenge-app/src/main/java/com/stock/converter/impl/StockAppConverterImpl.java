package com.stock.converter.impl;

import com.product.converter.ProductAppConverter;
import com.stock.converter.StockAppConverter;
import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.entity.ItemStock;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.ItemStockRequest;
import com.stock.entrypoint.data.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockAppConverterImpl implements StockAppConverter {
    private final ProductAppConverter productAppConverter;

    @Override
    public Stock toStockDomain(StockRequest request) {
        return Stock.builder()
                .name(request.getName())
                .build();
    }

    @Override
    public StockModel toStockModel(Stock entity) {
        return StockModel.builder()
                .items(entity.getItems())
                .priceStock(entity.getPriceStock())
                .products(entity.getProducts().stream().map(this.productAppConverter::toProductModel)
                        .collect(Collectors.toList()))
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public Stock toStockDomainByModel(StockModel model) {
        return Stock.builder()
                .id(model.getId())
                .items(model.getItems())
                .priceStock(model.getPriceStock())
                .products(model.getProducts().stream().map(this.productAppConverter::toProductDomainByModel)
                        .collect(Collectors.toList()))
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    @Override
    public ItemStock toItemStock(ItemStockRequest itemStockRequest) {
        return ItemStock.builder()
                .product(this.productAppConverter.toProductDomain(itemStockRequest.getProduct()))
                .quantity(itemStockRequest.getQuantity())
                .build();
    }
}