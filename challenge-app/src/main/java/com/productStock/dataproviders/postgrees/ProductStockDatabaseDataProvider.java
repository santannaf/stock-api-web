package com.productStock.dataproviders.postgrees;

import com.productStock.converter.ProductStockAppConverter;
import com.productStock.dataprovider.ProductStockDataProvider;
import com.productStock.dataproviders.postgrees.model.ProductStockModel;
import com.productStock.dataproviders.postgrees.repository.ProductStockAppDataProviderRepository;
import com.productStock.entity.CustomProductStock;
import com.productStock.entity.ProductStock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductStockDatabaseDataProvider implements ProductStockDataProvider {
    private final ProductStockAppConverter productStockAppConverter;
    private final ProductStockAppDataProviderRepository productStockAppDataProviderRepository;

    @Override
    public ProductStock insertProductIntoStock(ProductStock productStock) {
        ProductStockModel productStockModel = this.productStockAppConverter.toProductStockModel(productStock);
        ProductStockModel productStockSaved = this.productStockAppDataProviderRepository.save(productStockModel);
        return this.productStockAppConverter.toProductStockDomain(productStockSaved);
    }

    @Override
    public List<ProductStock> listAllProductsTheStocks() {
        return this.productStockAppDataProviderRepository.findAll().stream()
                .map(this.productStockAppConverter::toProductStockDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomProductStock> listStocksCustomsByIdStock(int idStock) {
        return this.productStockAppDataProviderRepository.listAllStocksWithProductsByIdStock(idStock).stream()
                .map(this.productStockAppConverter::toCustomProductStock)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomProductStock> listAllStocksCustoms() {
        return this.productStockAppDataProviderRepository.listAllStocksWithProducts().stream()
                .map(this.productStockAppConverter::toCustomProductStock)
                .collect(Collectors.toList());
    }
}