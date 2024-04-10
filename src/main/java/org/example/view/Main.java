package org.example.view;

import org.example.controller.CoinController;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int op = -1;
        while (true) {
            System.out.println("""
                    ******************************
                             Bienvenido
                    Elija la Divisa:
                     1.[ARS] Peso Argentino
                     2.[USD] Dolar Estadounidense
                     3.[EUR] Euro
                     4.[CLP] Peso Chileno
                     5.[BOB] Peso Boliviano
                     6.[PEN] Peso Peruano
                     7.[BRL] Real Brasileño
                     ******************************
                    """);
            CoinController.searchCoin(sc.nextInt());
            System.out.println("""
                    Elija a que Divisa desea convertir:
                     1.[ARS] Peso Argentino
                     2.[USD] Dolar Estadounidense
                     3.[EUR] Euro
                     4.[CLP] Peso Chileno
                     5.[BOB] Peso Boliviano
                     6.[PEN] Peso Peruano
                     7.[BRL] Real Brasileño
                     ******************************
                    """);
            CoinController.valueCoin = sc.nextInt();


            System.out.println("1 "+CoinController.getCoinSlt()[0]+" => $"+CoinController.getCoinSlt()[2]+" "+CoinController.getCoinSlt()[1]);
            System.out.println("Ingrese el monto a convertir: ");
            System.out.println("Son: "+CoinController.convertTo(sc.nextDouble())+" "+CoinController.getCoinSlt()[1]);
            System.out.println("""
                    Desea convertir otra Divisia?
                        1.Si
                        2.No
                    """);
            if (sc.next().contains("2")) {
                op = 0;
            }
            if (op == 0) {
                System.out.println("Muchas gracias por usar nuestro servicio, hasta luego :D.");
                break;
            }
        }
    }
}