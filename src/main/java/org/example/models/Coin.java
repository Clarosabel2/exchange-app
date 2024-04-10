package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private String symbol;
    private Map<String, Double> price;

    public Coin(CoinExchangeRate c) {
        this.symbol = c.base_code();
        this.price = c.conversion_rates();
    }
}
