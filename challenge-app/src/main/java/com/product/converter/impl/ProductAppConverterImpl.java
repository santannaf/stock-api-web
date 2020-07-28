package com.product.converter.impl;

import com.product.converter.ProductAppConverter;
import com.product.dataproviders.postgrees.model.ProductModel;
import com.product.entity.Product;
import com.product.entrypoint.data.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductAppConverterImpl implements ProductAppConverter {

    @Override
    public Product toProductDomain(ProductRequest request) {
        return Product.builder()
                .price(request.getPrice())
                .name(request.getName())
                .build();
    }

    @Override
    public ProductModel toProductModelWithId(Product entity) {
        return ProductModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public Product toProductDomainByModel(ProductModel model) {
        return Product.builder()
                .id(model.getId())
                .name(model.getName())
                .price(model.getPrice().setScale(2, RoundingMode.DOWN))
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }

    @Override
    public String now() {
        String AMERICA_SAO_PAULO = "America/Sao_Paulo";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        return LocalDateTime.now(ZoneId.of(AMERICA_SAO_PAULO))
                .format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }
}