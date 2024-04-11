package org.example.view;

import org.example.controller.CoinController;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int op = -1, opMenu=-1, a = 0;
        do{
            System.out.println("""
                    *****************************************
                                Exchange-app
                    *****************************************
                    Menu:
                        1.Convertir una divisa
                        2.Ver Historial de conversiones
                        3.Salir
                    *****************************************
                    """);
            opMenu = sc.nextInt();

            switch (opMenu){
                case 1:
                    while (true) {
                        try {
                            System.out.println("""
                            *****************************************
                                         Bienvenido                 *
                            --------------------------------------  *
                            Ingrese el numero de la opcion elegida. *
                            Elija la Divisa:                        *
                             1.[ARS] Peso Argentino                 *
                             2.[USD] Dolar Estadounidense           *
                             3.[EUR] Euro                           *
                             4.[CLP] Peso Chileno                   *
                             5.[BOB] Peso Boliviano                 *
                             6.[PEN] Peso Peruano                   *
                             7.[BRL] Real Brasileño                 *
                             ****************************************
                            """);
                            CoinController.searchCoin(sc.nextInt());

                            System.out.println("""
                            Elija a que Divisa desea convertir:     *
                             1.[ARS] Peso Argentino                 *
                             2.[USD] Dolar Estadounidense           *
                             3.[EUR] Euro                           *
                             4.[CLP] Peso Chileno                   *
                             5.[BOB] Peso Boliviano                 *
                             6.[PEN] Sol Peruano                    *
                             7.[BRL] Real Brasileño                 *
                            *****************************************
                            """);
                            a = sc.nextInt();
                            if (CoinController.statusOp(a)) {
                                CoinController.valueCoin = a;
                            }

                            System.out.println("1 " + CoinController.getCoinSlt()[0] + " => $" + CoinController.getCoinSlt()[2] + " " + CoinController.getCoinSlt()[1]);
                            System.out.println("Ingrese el monto a convertir: ");
                            System.out.println("Son: " + CoinController.convertTo(sc.nextDouble()) + " " + CoinController.getCoinSlt()[1]);
                            CoinController.saveRegistry();
                            do {
                                System.out.println("""
                                Desea convertir otra Divisia?
                                    1.Si
                                    2.No
                                """);
                                op = sc.nextInt();
                                if (op != 1 && op != 2) {
                                    System.out.println("Opcion Incorrecta, intenteto nuevamente...");
                                }

                            } while (op != 1 && op != 2);

                            if (op == 2) {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage() + ", intentelo nuevamente...");
                        }
                    }
                    break;
                case 2:
                    if (CoinController.exchangeRegistry.isEmpty()){
                        System.out.println("No hay registros");
                    }
                    else{
                        CoinController.exchangeRegistry.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.println("Hasta luego, muchas gracias por utilizar nuestro Servicio :D");
                    break;
                default:
                    System.out.println("Opcion Incorreta");
            }

        }while(opMenu!=3);


    }
}