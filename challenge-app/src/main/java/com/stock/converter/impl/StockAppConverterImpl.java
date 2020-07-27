package com.stock.converter.impl;

import com.product.converter.ProductAppConverter;
import com.product.dataproviders.postgrees.model.ProductModel;
import com.stock.converter.StockAppConverter;
import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.entity.ItemStock;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.ItemStockRequest;
import com.stock.entrypoint.data.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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
                .products(entity.getProducts().stream().map(this.productAppConverter::toProductModelWithId)
                        .collect(Collectors.toList()))
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public StockModel toStockWithIdModel(Stock entity) {
        return StockModel.builder()
                .id(entity.getId())
                .items(entity.getItems())
                .priceStock(entity.getPriceStock())
                .products(entity.getProducts().stream().map(this.productAppConverter::toProductModelWithId)
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
                .items(itemsIntoStock(model.getProducts()))
                .priceStock(valueStock(model.getProducts()).setScale(2, RoundingMode.DOWN))
                .products(model.getProducts().stream().map(this.productAppConverter::toProductDomainByModel)
                        .collect(Collectors.toList()))
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    @Override
    public Stock toStockDomainByModelAfterUpdate(StockModel model) {
        return Stock.builder()
                .id(model.getId())
                .items(model.getItems())
                .priceStock(model.getPriceStock().setScale(2, RoundingMode.DOWN))
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
                .quantity(itemStockRequest.getQuantity())
                .idProduct(itemStockRequest.getIdProduct())
                .build();
    }

    private int itemsIntoStock(List<ProductModel> products) {
        return products.stream().map(ProductModel::getQuantity).reduce(0, Integer::sum);
    }

    private BigDecimal valueStock(List<ProductModel> products) {
        return products.stream().map(o -> {
            var q = BigDecimal.valueOf(o.getQuantity());
            return q.multiply(o.getUnitPrice());
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}