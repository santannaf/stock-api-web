package com.stock.entity;

import java.util.HashMap;
import java.util.Map;

public enum RulesCriticismCoverage {
    MENOR_QUE_11_DIAS(0),
    ENTRE_10_E_13_DIAS(1),
    ENTRE_14_E_18_DIAS(2),
    ENTRE_19_E_23_DIAS(3),
    ACIMA_DE_23_DIAS(4);

    private int value;
    private static final Map<Integer, RulesCriticismCoverage> map = new HashMap<>();

    RulesCriticismCoverage(int id) {
        this.value = id;
    }

    static {
        for (RulesCriticismCoverage pageType : RulesCriticismCoverage.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static RulesCriticismCoverage fromValue(int value) {
        return map.get(value);
    }

    public static RulesCriticismCoverage fromValue(String value) {
        return map.get(Integer.parseInt(value));
    }

    public int getValue() {
        return value;
    }
}