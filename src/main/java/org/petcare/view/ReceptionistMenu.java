package org.petcare.view;

import org.petcare.controller.ClinicController;

import java.util.Scanner;

public class ReceptionistMenu implements Menu {
    private ClinicController controller;
    private Scanner scanner;

    public ReceptionistMenu(ClinicController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void displayOptions() {
        int option;
        do {
            // Mostrar las opciones disponibles para recepción.
            System.out.println();
            System.out.println("===== MENÚ RECEPCIÓN =====");
            System.out.println("1. Registrar dueño y mascota (perro, gato o ave)");
            System.out.println("2. Agendar una cita");
            System.out.println("3. Atender o cancelar una cita");
            System.out.println("4. Generar el comprobante de una cita atendida");
            System.out.println("5. Consultar el historial clínico de una mascota");
            System.out.println("6. Listar todas las mascotas y su sonido");
            System.out.println("7. Ver el total cobrado por veterinario");
            System.out.println("8. Dar de baja una mascota");
            System.out.println("9. Salir");

            option = readUserInput();

            // Ejecutar la acción seleccionada y repetir hasta elegir "Salir".
            switch (option) {
                case 1:
                    System.out.println("Registro de dueño y mascota.");
                    break;
                case 2:
                    System.out.println("Agendamiento de cita.");
                    break;
                case 3:
                    System.out.println("Atención o cancelación de cita.");
                    break;
                case 4:
                    System.out.println("Generación del comprobante.");
                    break;
                case 5:
                    System.out.println("Consulta del historial clínico.");
                    break;
                case 6:
                    System.out.println("Listado de mascotas y sonidos.");
                    break;
                case 7:
                    System.out.println("Consulta del total cobrado por veterinario.");
                    break;
                case 8:
                    System.out.println("Baja de mascota.");
                    break;
                case 9:
                    System.out.println("Saliendo del menú de recepción...");
                    break;
                default:
                    // readUserInput valida el rango; este caso protege contra cambios futuros.
                    System.out.println("Opción no reconocida.");
            }
        } while (option != 9);
    }

    @Override
    public int readUserInput() {
        // Solicitar una opción hasta recibir un número válido del menú.
        System.out.print("Opción: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Ingrese un número del 1 al 9.");
            scanner.nextLine();
            System.out.print("Opción: ");
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        while (option < 1 || option > 9) {
            System.out.println("Opción inválida. Ingrese un número del 1 al 9.");
            System.out.print("Opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número del 1 al 9.");
                scanner.nextLine();
                System.out.print("Opción: ");
            }
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }
}
