package org.example.controller;

import com.google.gson.Gson;
import org.example.models.Coin;
import org.example.models.CoinExchangeRate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CoinController {
    private static final String[] coins = {"ARS", "USD", "EUR", "CLP", "BOB", "PEN", "BRL"};
    private static Coin _coin = new Coin();
    public static int valueCoin;
    public static double amountC;
    public static List<String>exchangeRegistry = new ArrayList<String>();

    public static void searchCoin(int op) throws IOException, InterruptedException {
        if (statusOp(op)) {
            Gson gson = new Gson();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/bf1c002c4f4b62f4cab986c6/latest/" + coins[op - 1]))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            CoinExchangeRate coinER = gson.fromJson(response.body(), CoinExchangeRate.class);
            _coin = new Coin(coinER);
        }
    }

    public static double convertTo(double amount) {
        amountC = amount;
        return amount * _coin.getPrice().get(coins[valueCoin - 1]);
    }

    public static String[] getCoinSlt() {
        String[] info = new String[3];
        info[0] = _coin.getSymbol();
        info[1] = coins[valueCoin - 1];
        info[2] = String.valueOf(_coin.getPrice().get(info[1]));
        return info;
    }

    public static boolean statusOp(int op) {
        boolean flag = false;
        for (int i = 1; i < coins.length + 1; i++) {
            if (op == i) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NumberFormatException("Opcion Invalida");
        }
        return true;
    }

    public static void saveRegistry() {
        //FECHA HORA 1 USD => $*** ARS CANTIADAD: $USD = $ ARS
        LocalDateTime date = LocalDateTime.now();
        exchangeRegistry.add("Fecha y hora:"+ date + "|| 1" + _coin.getSymbol() + " => "+amountC+" "+coins[valueCoin-1]);
    }
}
