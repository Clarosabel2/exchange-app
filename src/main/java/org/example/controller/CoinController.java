package org.example.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.example.models.Coin;
import org.example.models.CoinExchangeRate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinController {
    private static final String[] coins = {"ARS", "USD", "EUR", "CLP", "BOB", "PEN", "BRL"};
    private static Coin _coin = new Coin();
    public static int valueCoin;

    public static void searchCoin(int op) throws IOException, InterruptedException {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/bf1c002c4f4b62f4cab986c6/latest/" + coins[op-1]))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        CoinExchangeRate coinER = gson.fromJson(response.body(), CoinExchangeRate.class);
        _coin = new Coin(coinER);
    }

    public static double convertTo(double amount) {
        double result = amount * _coin.getPrice().get(coins[valueCoin-1]);
        return result;
    }

    public static String[] getCoinSlt() {
        String[] info = new String[3];
        info[0] = _coin.getSymbol();
        info[1] = coins[valueCoin-1];
        info[2] = String.valueOf(_coin.getPrice().get(info[1]));
        return info;
    }
}
