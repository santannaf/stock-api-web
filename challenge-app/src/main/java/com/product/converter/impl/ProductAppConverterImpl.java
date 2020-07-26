package com.product.converter.impl;

import com.product.converter.ProductAppConverter;
import com.product.dataproviders.postgrees.model.ProductModel;
import com.product.entity.Product;
import com.product.entrypoint.data.request.ProductRequest;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class ProductAppConverterImpl implements ProductAppConverter {
    @Override
    public ProductModel toProductModel(Product entity) {
        return ProductModel.builder()
                .name(entity.getName())
                .unitPrice(entity.getUnitPrice())
                .quantity(entity.getQuantity())
                .build();
    }

    @Override
    public Product toProductDomainByModel(ProductModel model) {
        return Product.builder()
                .id(model.getId())
                .name(model.getName())
                .unitPrice(model.getUnitPrice().setScale(2, RoundingMode.DOWN))
                .quantity(model.getQuantity())
                .build();
    }

    @Override
    public Product toProductDomain(ProductRequest request) {
        return Product.builder()
                .unitPrice(request.getPrice())
                .name(request.getName())
                .build();
    }
}