package org.petcare.util;

import java.util.Scanner;

/**
 * Clase utilitaria para la lectura y validación de datos por consola.
 */
public final class InputHelper {

    /**
     * Impide crear instancias de esta clase utilitaria.
     */
    private InputHelper() {
    }

    /**
     * Lee una línea de texto después de mostrar su etiqueta.
     */
    public static String readText(Scanner scanner, String label) {
        System.out.print(label + ": ");
        return scanner.nextLine();
    }

    /**
     * Lee un entero, repitiendo hasta recibir una entrada válida.
     */
    public static int readInt(Scanner scanner, String label) {
        System.out.print(label + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Ingrese un número entero.");
            scanner.nextLine();
            System.out.print(label + ": ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    /**
     * Lee un decimal, repitiendo hasta recibir una entrada válida.
     */
    public static double readDecimal(Scanner scanner, String label) {
        System.out.print(label + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Ingrese un número decimal.");
            scanner.nextLine();
            System.out.print(label + ": ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    /**
     * Lee y valida una opción numérica ingresada por el usuario.
     * Asegura que la opción se encuentre entre 1 y el valor máximo permitido.
     *
     * @param scanner El escáner de entrada.
     * @param maximum El valor máximo permitido para la opción.
     * @return El número entero validado ingresado por el usuario.
     */
    public static int readOption(Scanner scanner, int maximum) {
        int option = readInt(scanner, "Opción");
        while (option < 1 || option > maximum) {
            System.out.println("Ingrese un número del 1 al " + maximum + ".");
            option = readInt(scanner, "Opción");
        }
        return option;
    }
}