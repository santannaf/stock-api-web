package com.stock.dataproviders.postgrees.repository;

import com.stock.dataproviders.postgrees.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAppDataProviderRepository extends JpaRepository<StockModel, Integer> {
}