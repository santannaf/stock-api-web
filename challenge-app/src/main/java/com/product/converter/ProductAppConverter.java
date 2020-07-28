package com.product.converter;

import com.product.dataproviders.postgrees.model.ProductModel;
import com.product.entity.Product;
import com.product.entrypoint.data.request.ProductRequest;

public interface ProductAppConverter {

    Product toProductDomain(ProductRequest request);
    ProductModel toProductModelWithId(Product entity);
    Product toProductDomainByModel(ProductModel model);
    String now();

//    ProductStockModel toProductModel(ProductStock entity);
//    ProductStock toProductDomainByModel(ProductStockModel model);
//
//    ProductStockModel toProductStockModelWithId(Product entity);
}