package com.stock.dataproviders.postgrees;

import com.stock.converter.StockAppConverter;
import com.stock.dataprovider.StockDataProvider;
import com.stock.dataproviders.postgrees.model.StockModel;
import com.stock.dataproviders.postgrees.repository.StockAppDataProviderRepository;
import com.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockDatabaseDataProvider implements StockDataProvider {
    private final StockAppDataProviderRepository stockAppDataProviderRepository;
    private final StockAppConverter stockAppConverter;

    @Override
    public Stock create(Stock stock) {
        StockModel stockModel = stockAppConverter.toStockModel(stock);
        StockModel stockSaved = this.stockAppDataProviderRepository.save(stockModel);
        return this.stockAppConverter.toStockDomainByModel(stockSaved);
    }

    @Override
    public Optional<Stock> findById(int id) {
        return this.stockAppDataProviderRepository.findById(id)
                .map(this.stockAppConverter::toStockDomainByModel);
    }

    @Override
    public List<Stock> findAll() {
        return this.stockAppDataProviderRepository.findAll()
                .stream()
                .map(this.stockAppConverter::toStockDomainByModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Stock> put(Stock stock) {
        StockModel stockModel = this.stockAppConverter.toStockModel(stock);
        StockModel stockSaved = this.stockAppDataProviderRepository.save(stockModel);
        return Optional.ofNullable(this.stockAppConverter.toStockDomainByModel(stockSaved));
    }

    @Override
    public void delete(int id) {
        this.stockAppDataProviderRepository.findById(id)
                .ifPresent(this.stockAppDataProviderRepository::delete);
    }
}