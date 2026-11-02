package org.petcare.view;

import org.petcare.controller.RegistrationController;
import org.petcare.util.InputHelper;

import java.util.Scanner;

/**
 * Menú general de operaciones de la clínica veterinaria.
 */
public class AdminMenu implements Menu {
    private static final int EXIT_OPTION = 11;
    private final RegistrationView registrationHelper;
    private final AppointmentView appointmentView;
    private final ClinicOperationsView operationsView;
    private final Scanner scanner;

    /**
     * Crea el menú básico con registro y citas.
     */
    public AdminMenu(RegistrationController controller, AppointmentView appointmentView,
                     Scanner scanner) {
        this(controller, appointmentView, null, scanner);
    }

    /**
     * Crea el menú completo conectando todas las vistas del sistema.
     *
     * @param controller      controlador de registros
     * @param appointmentView vista de citas
     * @param operationsView  vista de operaciones de clínica (opciones 6 a 10)
     * @param scanner         lector de consola
     */
    public AdminMenu(RegistrationController controller, AppointmentView appointmentView,
                     ClinicOperationsView operationsView, Scanner scanner) {
        this.registrationHelper = new RegistrationView(controller, scanner);
        this.appointmentView = appointmentView;
        this.operationsView = operationsView;
        this.scanner = scanner;
    }

    @Override
    /** Muestra opciones y ejecuta la acción elegida hasta salir. */
    public void displayOptions() {
        int option;
        do {
            showOptions();
            option = readUserInput();
            executeOption(option);
        } while (option != EXIT_OPTION);
    }

    /**
     * Ejecuta la opción seleccionada por el usuario delegando a la vista correspondiente.
     */
    private void executeOption(int option) {
        switch (option) {
            case 1:
                registrationHelper.registerClientWithPet();
                break;
            case 2:
                registrationHelper.registerPetToExistingClient();
                break;
            case 3:
                registrationHelper.registerVeterinarian();
                break;
            case 4:
                appointmentView.displayScheduleAppointment();
                break;
            case 5:
                appointmentView.displayManageAppointment();
                break;
            case 6:
                operationsView.displayGenerateReceipt();
                break;
            case 7:
                operationsView.displayMedicalHistory();
                break;
            case 8:
                operationsView.displayPetsAndSounds();
                break;
            case 9:
                operationsView.displayTotalByVeterinarian();
                break;
            case 10:
                operationsView.displayRemovePet();
                break;
            case EXIT_OPTION:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no reconocida.");
        }
    }

    private void showOptions() {
        // La opción 2 registra mascotas de dueños que ya existen.
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Registrar dueño y mascota");
        System.out.println("2. Registrar mascota");
        System.out.println("3. Registrar veterinario y asignarlo a la clínica");
        System.out.println("4. Agendar una cita");
        System.out.println("5. Atender o cancelar una cita");
        System.out.println("6. Generar el comprobante de una cita atendida");
        System.out.println("7. Consultar el historial clínico de una mascota");
        System.out.println("8. Listar todas las mascotas y su sonido");
        System.out.println("9. Ver el total cobrado por veterinario");
        System.out.println("10. Dar de baja una mascota");
        System.out.println("11. Salir");
    }

    @Override
    /** Lee una opción válida dentro del rango del menú. */
    public int readUserInput() {
        return InputHelper.readOption(scanner, EXIT_OPTION);
    }
}