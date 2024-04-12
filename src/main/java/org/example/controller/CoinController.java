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

    //Divisas con las que opera la app
    private static final String[] coins = {"ARS", "USD", "EUR", "CLP", "BOB", "PEN", "BRL"};
    //Variables que las utilizo como cache
    private static Coin _coin = new Coin();
    public static int valueCoin;
    public static Double amountC;
    public static List<String>exchangeRegistry = new ArrayList<String>();


    //Esta funcion se entrega de buscar la divisa selecciona por el usuario y crea el objeto Coin
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

    //Esta funcion se encarga convertir la cantidad proporcionada por el usuario y la convierte a la divisa deseada
    public static double convertTo(double amount) {
        amountC = amount;
        return amount * _coin.getPrice().get(coins[valueCoin - 1]);
    }

    public static String[] getCoinSlt() {
        String[] info = new String[4];
        info[0] = _coin.getSymbol();
        info[1] = coins[valueCoin - 1];
        info[2] = String.valueOf(_coin.getPrice().get(info[1]));
        return info;
    }
    //Verifica que la opcion selecccionada este dentro de las divisas soportadas
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
    //Guarda un registro de las solicitudes realizadas
    public static void saveRegistry() {
        LocalDateTime date = LocalDateTime.now();
        exchangeRegistry.add("Fecha y hora:"+ date + "||"+amountC + _coin.getSymbol() + " => "+convertTo(amountC)+" "+coins[valueCoin-1]);
    }
}
