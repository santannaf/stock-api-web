package com.product.converter;

import com.product.dataproviders.postgrees.model.ProductModel;
import com.product.entity.Product;
import com.product.entrypoint.data.request.ProductRequest;

public interface ProductAppConverter {
    ProductModel toProductModel(Product entity);
    Product toProductDomainByModel(ProductModel model);
    Product toProductDomain(ProductRequest request);
}