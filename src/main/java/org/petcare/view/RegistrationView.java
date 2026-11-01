package org.petcare.view;

import org.petcare.controller.RegistrationController;
import org.petcare.model.person.Client;
import org.petcare.model.pet.Pet;
import org.petcare.util.InputHelper;

import java.util.Scanner;

/**
 * Clase auxiliar encargada de interactuar con el usuario mediante consola
 * para llevar a cabo los flujos de registro.
 */
public class RegistrationView {
    /**
     * Controlador que ejecuta los registros.
     */
    private final RegistrationController controller;
    /**
     * Entrada de consola compartida con el menú.
     */
    private final Scanner scanner;

    /**
     * Crea el auxiliar con el controlador y lector de entrada indicados.
     */
    public RegistrationView(RegistrationController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    /**
     * Registra un cliente y, a continuación, una mascota asociada.
     */
    public void registerClientWithPet() {
        try {
            // 1. Capturar datos del cliente y registrar
            Client newClient = controller.registerClient(
                    InputHelper.readText(scanner, "Nombre"),
                    InputHelper.readText(scanner, "Apellido"),
                    InputHelper.readText(scanner, "Teléfono"),
                    InputHelper.readText(scanner, "Correo"),
                    InputHelper.readText(scanner, "Dirección")
            );
            System.out.println("Dueño registrado exitosamente con ID: " + newClient.getId());

            // 2. Capturar mascota y vincularla al ID recién creado
            int petType = readPetType();
            Pet newPet = registerPet(newClient.getId(), petType);
            System.out.println("Mascota registrada exitosamente con ID: " + newPet.getId());

        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /**
     * Registra una mascota para un cliente previamente existente.
     */
    public void registerPetToExistingClient() {
        if (!controller.hasClients()) {
            System.out.println("No hay dueños registrados. Use la opción 1 primero.");
            return;
        }

        printClients();
        int clientId = InputHelper.readInt(scanner, "ID del dueño");

        if (controller.findClientById(clientId) == null) {
            System.out.println("No existe un dueño con ese ID.");
            return;
        }

        // La validación del dominio se traduce en un mensaje comprensible para consola.
        try {
            int petType = readPetType();
            Pet pet = registerPet(clientId, petType);
            System.out.println("Mascota registrada exitosamente con ID: " + pet.getId());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /**
     * Solicita el tipo de mascota que se va a registrar.
     */
    private int readPetType() {
        System.out.println("Tipo de mascota: 1) Perro, 2) Gato o 3) Ave exotica");
        return InputHelper.readOption(scanner, 3);
    }

    /**
     * Recopila los datos específicos y delega la creación al controlador.
     */
    private Pet registerPet(int clientId, int type) {
        String name = InputHelper.readText(scanner, "Nombre de la mascota");
        int age = InputHelper.readInt(scanner, "Edad");
        double weight = InputHelper.readDecimal(scanner, "Peso");

        switch (type) {
            case 1:
                return controller.registerDog(clientId, name, age, weight,
                        InputHelper.readText(scanner, "Raza"),
                        InputHelper.readText(scanner, "Nivel de energía"));
            case 2:
                return controller.registerCat(clientId, name, age, weight, readDomestic());
            default:
                return controller.registerExoticBird(clientId, name, age, weight,
                        InputHelper.readDecimal(scanner, "Envergadura"));
        }
    }

    /**
     * Lee si un gato está clasificado como doméstico.
     */
    private boolean readDomestic() {
        System.out.println("¿Es doméstico? 1 = sí, 2 = no");
        return InputHelper.readOption(scanner, 2) == 1;
    }

    /**
     * Imprime los clientes disponibles para seleccionar un responsable.
     */
    private void printClients() {
        for (Client client : controller.getAllClients()) {
            System.out.println(client.getId() + " - " + client.getFirstName() + " " + client.getLastName());
        }
    }
}