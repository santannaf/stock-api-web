package com.product.dataproviders;

import com.product.converter.ProductAppConverter;
import com.product.dataprovider.ProductDataProvider;
import com.product.dataproviders.postgrees.model.ProductModel;
import com.product.dataproviders.postgrees.repository.ProductAppDataProviderRepository;
import com.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductDatabaseDataProvider implements ProductDataProvider {
    private final ProductAppConverter productAppConverter;
    private final ProductAppDataProviderRepository productAppDataProviderRepository;

    @Override
    public Product create(Product product) {
        ProductModel productModel = this.productAppConverter.toProductModelWithId(product);
        ProductModel productSaved = this.productAppDataProviderRepository.save(productModel);
        return this.productAppConverter.toProductDomainByModel(productSaved);
    }

    @Override
    public List<Product> listAll() {
        return this.productAppDataProviderRepository.findAll().stream()
                .map(this.productAppConverter::toProductDomainByModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(int id) {
        return this.productAppDataProviderRepository.findById(id).map(this.productAppConverter::toProductDomainByModel);
    }

    @Override
    public Optional<Product> put(Product product) {
        return this.productAppDataProviderRepository.findById(product.getId()).map(productFound -> {
            productFound.setName(product.getName().isEmpty() ? productFound.getName() : product.getName());
            productFound.setPrice(product.getPrice().doubleValue() == 0L ? productFound.getPrice() : product.getPrice());
            productFound.setUpdatedAt(this.productAppConverter.now());
            return this.productAppDataProviderRepository.save(productFound);
        }).map(this.productAppConverter::toProductDomainByModel);
    }

    @Override
    public void delete(int id) {
        this.productAppDataProviderRepository.deleteById(id);
    }
}