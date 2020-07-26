package com.product.dataproviders.postgrees.repository;

import com.product.dataproviders.postgrees.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAppDataProviderRepository extends JpaRepository<ProductModel, Integer> {
}