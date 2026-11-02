package org.petcare.app;

import org.petcare.controller.AppointmentController;
import org.petcare.controller.ClinicOperationsController;
import org.petcare.controller.RegistrationController;
import org.petcare.model.clinic.Clinic;
import org.petcare.view.AdminMenu;
import org.petcare.view.AppointmentView;
import org.petcare.view.ClinicOperationsView;
import org.petcare.view.Menu;

import java.util.Scanner;

/**
 * Clase principal que inicia el sistema de gestión veterinaria PetCare.
 */
public class Main {
    /**
     * Inicia la aplicación y presenta el menú correspondiente al perfil elegido.
     *
     * @param args argumentos recibidos desde la línea de comandos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic petCareClinic = new Clinic("PetCare", 5);

        // Inicialización de controladores
        RegistrationController controller = new RegistrationController(petCareClinic);
        AppointmentController appointmentController = new AppointmentController(petCareClinic);
        ClinicOperationsController operationsController =
                new ClinicOperationsController(petCareClinic);

        // Inicialización de vistas auxiliares
        AppointmentView appointmentView =
                new AppointmentView(appointmentController, petCareClinic, scanner);
        ClinicOperationsView operationsView =
                new ClinicOperationsView(operationsController, petCareClinic, scanner);

        System.out.println("=== SISTEMA DE GESTIÓN VETERINARIA PETCARE ===");
        Menu currentMenu = new AdminMenu(controller, appointmentView, operationsView, scanner);

        // Ejecutamos el menú seleccionado sin que el Main sepa los detalles internos
        currentMenu.displayOptions();

        scanner.close();
    }
}