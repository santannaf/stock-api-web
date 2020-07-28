package com.stock.entity;

import com.stock.dataprovider.CriticismCoverageDataProvider;

import javax.inject.Named;

@Named
public class CriticismCoverage implements CriticismCoverageDataProvider {
    @Override
    public String calculateCoverage(int consumer, int quantity) {
        if (quantity <= consumer * 10) {
            return "Perigo - Estoque com capacidade menor que 10 dias";
        } else if (quantity <= consumer * 13) {
            return "Atenção - Estoque com capacidade entre 10 e 13 dias";
        } else if (quantity <= consumer * 18) {
            return "Cobertura Ideal - Estoque com cobertura ideal de 14 a 18 dias";
        } else if (quantity <= consumer * 23) {
            return "Atenção - Estoque com capacidade entre 19 e 23 dias";
        } else {
            return "Perigo - Estoque com excesso de terminais";
        }
    }
}