package org.petcare.view;

import org.petcare.controller.RegistrationController;
import org.petcare.util.InputHelper;

import java.util.Scanner;

/**
 * Menú de operaciones disponibles para el perfil de recepción.
 */
public class ReceptionistMenu implements Menu {
    private static final int EXIT_OPTION = 10;
    private final RegistrationView registrationHelper;
    private final Scanner scanner;

    /**
     * Crea el menú y sus auxiliares de registro.
     */
    public ReceptionistMenu(RegistrationController controller, Scanner scanner) {
        this.registrationHelper = new RegistrationView(controller, scanner);
        this.scanner = scanner;
    }

    @Override
    /** Muestra opciones y procesa acciones hasta seleccionar salir. */
    public void displayOptions() {
        int option;
        do {
            showOptions();
            option = readUserInput();
            // Las operaciones aún no implementadas se anuncian al usuario.
            switch (option) {
                case 1:
                    registrationHelper.registerClientWithPet();
                    break;
                case 2:
                    registrationHelper.registerPetToExistingClient();
                    break;
                case 3:
                    System.out.println("Agendamiento de cita.");
                    break;
                case 4:
                    System.out.println("Atención o cancelación de cita.");
                    break;
                case 5:
                    System.out.println("Generación del comprobante.");
                    break;
                case 6:
                    System.out.println("Consulta del historial clínico.");
                    break;
                case 7:
                    System.out.println("Listado de mascotas y sonidos.");
                    break;
                case 8:
                    System.out.println("Consulta del total cobrado por veterinario.");
                    break;
                case 9:
                    System.out.println("Baja de mascota.");
                    break;
                case EXIT_OPTION:
                    System.out.println("Saliendo del menú de recepción...");
                    break;
                default:
                    System.out.println("Opción no reconocida.");
            }
        } while (option != EXIT_OPTION);
    }

    private void showOptions() {
        // La opción 2 registra mascotas de dueños que ya existen.
        System.out.println("\n===== MENÚ RECEPCIÓN =====");
        System.out.println("1. Registrar dueño y mascota");
        System.out.println("2. Registrar mascota");
        System.out.println("3. Agendar una cita");
        System.out.println("4. Atender o cancelar una cita");
        System.out.println("5. Generar el comprobante de una cita atendida");
        System.out.println("6. Consultar el historial clínico de una mascota");
        System.out.println("7. Listar todas las mascotas y su sonido");
        System.out.println("8. Ver el total cobrado por veterinario");
        System.out.println("9. Dar de baja una mascota");
        System.out.println("10. Salir");
    }

    @Override
    /** Lee una opción válida dentro del rango del menú. */
    public int readUserInput() {
        return InputHelper.readOption(scanner, EXIT_OPTION);
    }
}
