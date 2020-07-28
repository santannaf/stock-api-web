package com.stock.usecase;

import com.stock.entity.RulesCriticismCoverage;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Collections;
import java.util.Map;

@Named
@RequiredArgsConstructor
public class CriticismCoverageUseCase {

    public Map<Integer, String> calculateCoverage(int consumer, int quantity) {
        if (quantity <= consumer * 10) {
            return Collections.singletonMap(RulesCriticismCoverage.MENOR_QUE_11_DIAS.getValue(),
                                                        "Perigo - Estoque com capacidade menor que 10 dias");
        } else if (quantity <= consumer * 13) {
            return Collections.singletonMap(RulesCriticismCoverage.ENTRE_10_E_13_DIAS.getValue(),
                    "Atenção - Estoque com capacidade entre 10 e 13 dias");
        } else if (quantity <= consumer * 18) {
            return Collections.singletonMap(RulesCriticismCoverage.ENTRE_14_E_18_DIAS.getValue(),
                    "Cobertura Ideal - Estoque com cobertura ideal de 14 a 18 dias");
        } else if (quantity <= consumer * 23) {
            return Collections.singletonMap(RulesCriticismCoverage.ENTRE_19_E_23_DIAS.getValue(),
                    "Atenção - Estoque com capacidade entre 19 e 23 dias");
        } else {
            return Collections.singletonMap(RulesCriticismCoverage.ACIMA_DE_23_DIAS.getValue(),
                    "Perigo - Estoque com excesso de terminais");
        }
    }
}