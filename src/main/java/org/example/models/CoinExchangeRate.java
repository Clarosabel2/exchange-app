package org.example.models;

import java.util.Map;

public record CoinExchangeRate(
        String base_code,
        Map<String, Double> conversion_rates) {
}
