package org.petcare.app;

import org.petcare.controller.ClinicController;
import org.petcare.view.AdminMenu;
import org.petcare.view.Menu;
import org.petcare.view.ReceptionistMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClinicController controller = new ClinicController();

        System.out.println("=== SISTEMA DE GESTIÓN VETERINARIA PETCARE ===");
        System.out.println("Seleccione el perfil de acceso:");
        System.out.println("1. Administrador");
        System.out.println("2. Recepcionista");
        System.out.print("Opción: ");

        int choice;
        while (!scanner.hasNextInt()) {
            System.out.print("Opción inválida. Seleccione 1 o 2: ");
            scanner.nextLine();
        }
        choice = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        while (choice != 1 && choice != 2) {
            System.out.print("Opción inválida. Seleccione 1 o 2: ");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print("Opción inválida. Seleccione 1 o 2: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        Menu currentMenu;

        // Evaluamos el rol para decidir qué menú instanciar (Polimorfismo)
        if (choice == 1) {
            currentMenu = new AdminMenu(controller, scanner);
        } else {
            currentMenu = new ReceptionistMenu(controller, scanner);
        }

        // Ejecutamos el menú seleccionado sin que el Main sepa los detalles internos
        currentMenu.displayOptions();

        scanner.close();
    }
}
