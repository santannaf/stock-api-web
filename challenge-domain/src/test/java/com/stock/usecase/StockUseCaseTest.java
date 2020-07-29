package com.stock.usecase;

import com.stock.dataprovider.StockDataProvider;
import com.stock.entity.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockUseCaseTest {
    @Mock
    StockDataProvider stockDataProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateStock() {
        Stock request = getStock();
        when(stockDataProvider.create(request)).thenReturn(request);
        Stock stock = stockDataProvider.create(request);
        Assert.assertEquals(request, stock);
    }

    @Test
    public void shouldValidNullPointerExceptionCreateStock() {
        when(stockDataProvider.create(null)).thenThrow(NullPointerException.class);
        Assert.assertThrows(NullPointerException.class, () -> {
            stockDataProvider.create(null);
        });
    }

    private static Stock getStock() {
        return Stock.builder()
                .id(ThreadLocalRandom.current().nextInt())
                .name("Teste Estoque")
                .build();
    }
}