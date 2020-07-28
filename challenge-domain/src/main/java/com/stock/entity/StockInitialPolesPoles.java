package com.stock.entity;

import com.stock.dataprovider.StockInitialPolesDataProvider;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@RequiredArgsConstructor
public class StockInitialPolesPoles implements StockInitialPolesDataProvider {
    @Override
    public List<String> getPoles() {
        return Arrays.asList("São Paulo", "Minas Gerais", "Rio de Janeiro", "Bahia", "Paraná", "Rio Grande do Sul",
                "Pernambuco", "Ceará", "Pará", "Santa Catarina", "Maranhão", "Goiás", "Amazonas", "Espírito Santo",
                "Paraíba", "Rio Grande do Norte", "Mato Grosso", "Alagoas", "Piauí", "Distrito Federal",
                "Mato Grosso do Sul", "Sergipe", "Rondônia", "Tocantins", "Acre", "Amapá", "Roraima");
    }
}