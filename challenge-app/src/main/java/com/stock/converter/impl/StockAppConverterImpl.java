package com.stock.converter.impl;

import com.stock.converter.StockAppConverter;
import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockAppConverterImpl implements StockAppConverter {

    @Override
    public Stock toStockDomain(StockRequest request) {
        return Stock.builder()
                .name(request.getName())
                .build();
    }

    @Override
    public StockModel toStockModel(Stock entity) {
        return StockModel.builder()
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public StockModel toStockModelWithId(Stock entity) {
        return StockModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public Stock toStockDomainByModel(StockModel model) {
        return Stock.builder()
                .id(model.getId())
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    @Override
    public Stock toStockDomainByModelAfterUpdate(StockModel model) {
        return Stock.builder()
                .id(model.getId())
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

//    @Override
//    public ItemStock toItemStock(ItemStockRequest itemStockRequest) {
//        return ItemStock.builder()
//                .quantity(itemStockRequest.getQuantity())
//                .idProduct(itemStockRequest.getIdProduct())
//                .build();
//    }

//    private int itemsIntoStock(List<ProductStockModel> products) {
//        return products.stream().map(ProductStockModel::getQuantity).reduce(0, Integer::sum);
//    }
//
//    private BigDecimal valueStock(List<ProductStockModel> products) {
//        return products.stream().map(o -> {
//            BigDecimal q = BigDecimal.valueOf(o.getQuantity());
////            return q.multiply(o.getUnitPrice());
//            return BigDecimal.ZERO;
//        }).reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
}