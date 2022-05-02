package es.asdj;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int longitud,casilla,contador = 0,contador2 = 0;
        char[] tablero;
        char[] tableroOculto;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Bienvenido al buscaminas");
        longitud = pedirNumeroEnRango(entrada, 5, 20,"¿Qué tan largo quieres el tablero? [De 5 a 20] ");
        tablero = contruirTablero(longitud);
        tableroOculto = construirTableroOculto(longitud);
        mostrarTablero(tableroOculto);


        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] != '*') {
                contador++;
            }
        }
        do {
            casilla = pedirNumeroEnRango(entrada, 1, tablero.length + 1,"¿Qué casilla quieres destapar? ");

            if (tablero[casilla - 1] == '1' || tablero[casilla - 1] == '2' || tablero[casilla - 1] == '0') {
                tableroOculto[casilla - 1] = tablero[casilla - 1];
                contador2++;
            }

            mostrarTablero(tableroOculto);
        } while ((tablero[casilla - 1] == '1' || tablero[casilla - 1] == '2' || tablero[casilla - 1] == '0') && contador != contador2);

        if (contador == contador2) {
            System.out.println("Has ganado");
        } else {
            System.out.println("Has perdido");
        }
    }


    private static int pedirNumeroEnRango(Scanner entrada, int min ,int max,String mensaje) {
        int numero;
        do {
            System.out.print(mensaje);
            numero = entrada.nextInt();
        } while (numero < min || numero > max);

        return numero;
    }

    private static char[] contruirTablero(int tamanyo) {
        char[] tablero = new char[tamanyo];
        int minaoNumero;

        for (int i = 0; i < tamanyo; i++) {
            minaoNumero = (int) (Math.random() * 2);
            if (minaoNumero == 0) {
                tablero[i] = '*';
            } else if (minaoNumero == 1){
                tablero[i] = '0';
            }
        }

        for (int i = 0; i < tamanyo; i++) {
            if (i != 0 && i != tablero.length - 1 && tablero[i] == '0') {
                if (tablero[i - 1] == '*' && tablero[i + 1] != '*' || tablero[i + 1] == '*' && tablero[i - 1] != '*') {
                    tablero[i] = '1';
                } else if (tablero[i - 1] == '*' && tablero[i + 1] == '*') {
                    tablero[i] = '2';
                }
            } else if (i == 0 && tablero[i] == '0') {
                if (tablero[i + 1] == '*'){
                    tablero[i] = '1';
                }
            } else if (i == tablero.length - 1) {
                if (tablero[i - 1] == '*' && tablero[i] == '0'){
                    tablero[i] = '1';
                }
            }
        }

        return tablero;
    }

    private static char[] construirTableroOculto(int tamanyo) {
        char[] tablero = new char[tamanyo];

        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = '·';
        }

        return tablero;
    }

    private static void mostrarTablero(char[] tablero) {
        for (int i = 0; i < tablero.length; i++){
            if (i < 9) {
                System.out.print("  " + (i + 1) + " ");
            } else {
                System.out.print(" " + (i + 1) + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("| " + tablero[i] + " ");
        }
        System.out.println("|");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

}
