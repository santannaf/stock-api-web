package com.productStock.dataproviders.postgrees.repository;

import com.productStock.dataproviders.postgrees.model.CustomProductStockModel;
import com.productStock.dataproviders.postgrees.model.ProductStockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStockAppDataProviderRepository extends JpaRepository<ProductStockModel, Integer> {
    @Query(value = "SELECT new com.productStock.dataproviders.postgrees.model.CustomProductStockModel(PS.stock.id, " +
            "PS.stock.name, PS.product.name, PS.quantity, PS.product.price) FROM ProductStockModel PS " +
            "WHERE PS.stock.id = :idStock ")
    List<CustomProductStockModel> listAllStocksWithProductsByIdStock(int idStock);
    @Query(value = "SELECT new com.productStock.dataproviders.postgrees.model.CustomProductStockModel(PS.stock.id, " +
            "PS.stock.name, PS.product.name, PS.quantity, PS.product.price) FROM ProductStockModel PS ")
    List<CustomProductStockModel> listAllStocksWithProducts();
}