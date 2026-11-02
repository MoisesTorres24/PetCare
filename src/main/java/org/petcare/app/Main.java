package org.petcare.app;

import org.petcare.controller.RegistrationController;
import org.petcare.controller.AppointmentController;
import org.petcare.model.clinic.Clinic;
import org.petcare.view.AppointmentView;
import org.petcare.util.InputHelper;
import org.petcare.view.AdminMenu;
import org.petcare.view.Menu;
import org.petcare.view.ReceptionistMenu;

import java.util.Scanner;

public class Main {
    /**
     * Inicia la aplicación y presenta el menú correspondiente al perfil elegido.
     *
     * @param args argumentos recibidos desde la línea de comandos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic petCareClinic = new Clinic("PetCare", 5);
        RegistrationController controller = new RegistrationController(petCareClinic);
        AppointmentController appointmentController = new AppointmentController(petCareClinic);
        AppointmentView appointmentView = new AppointmentView(appointmentController, controller, scanner);

        System.out.println("=== SISTEMA DE GESTIÓN VETERINARIA PETCARE ===");
        System.out.println("Seleccione el perfil de acceso:");
        System.out.println("1. Administrador");
        System.out.println("2. Recepcionista");
        int choice = InputHelper.readOption(scanner, 2);

        Menu currentMenu;

        // Evaluamos el rol para decidir qué menú instanciar (Polimorfismo)
        if (choice == 1) {
            currentMenu = new AdminMenu(controller, appointmentView, scanner);
        } else {
            currentMenu = new ReceptionistMenu(controller, scanner);
        }

        // Ejecutamos el menú seleccionado sin que el Main sepa los detalles internos
        currentMenu.displayOptions();

        scanner.close();
    }
}
