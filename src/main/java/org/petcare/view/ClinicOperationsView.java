package org.petcare.view;

import org.petcare.controller.ClinicOperationsController;
import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.clinic.Receipt;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.util.InputHelper;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Vista de consola encargada de gestionar la emisión de comprobantes,
 * consulta de historial clínico, sonidos de mascotas, cobros y bajas.
 */
public class ClinicOperationsView {
    /**
     * Controlador de operaciones de la clínica.
     */
    private final ClinicOperationsController operationsController;
    /**
     * Clínica con los datos generales para listados previos.
     */
    private final Clinic clinic;
    /**
     * Lector compartido de consola.
     */
    private final Scanner scanner;

    /**
     * Construye la vista de operaciones con su controlador y escáner.
     *
     * @param operationsController controlador de operaciones de clínica
     * @param clinic               clínica activa
     * @param scanner              lector de entrada por consola
     */
    public ClinicOperationsView(ClinicOperationsController operationsController,
                                Clinic clinic, Scanner scanner) {
        this.operationsController = operationsController;
        this.clinic = clinic;
        this.scanner = scanner;
    }

    /**
     * Opción 6: Solicita una cita atendida y muestra su comprobante de pago.
     */
    public void displayGenerateReceipt() {
        System.out.println("=== Generar comprobante de cita atendida ===");
        if (!showCompletedAppointments()) {
            return;
        }
        try {
            int appointmentId = InputHelper.readInt(scanner, "ID de la cita atendida");
            Receipt receipt = operationsController
                    .generateReceiptForAppointment(appointmentId);
            receipt.generateTicket();
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /**
     * Opción 7: Muestra el historial clínico y total cobrado de una mascota.
     */
    public void displayMedicalHistory() {
        System.out.println("=== Consultar historial clínico de una mascota ===");
        if (!showAvailablePets()) {
            return;
        }
        try {
            int petId = InputHelper.readInt(scanner, "ID de la mascota");
            Pet pet = operationsController.findPetById(petId);
            Set<Appointment> history = operationsController.getPetMedicalHistory(petId);
            printHistoryRecords(pet, history);
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /**
     * Opción 8: Lista todas las mascotas y ejecuta su sonido polimórficamente.
     */
    public void displayPetsAndSounds() {
        System.out.println("=== Listado de mascotas y su sonido ===");
        List<Pet> pets = operationsController.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No hay mascotas registradas en la clínica.");
            return;
        }
        // Recorremos la lista polimórfica invocando makeSound() sin usar instanceof
        for (Pet pet : pets) {
            System.out.print(pet.toString() + " -> Sonido: ");
            pet.makeSound();
        }
    }

    /**
     * Opción 9: Muestra el total cobrado por cada veterinario registrado.
     */
    public void displayTotalByVeterinarian() {
        System.out.println("=== Total cobrado por veterinario ===");
        if (clinic.getAllVeterinarians().isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
            return;
        }
        // Calculamos y mostramos el acumulado de cada veterinario
        for (Veterinarian vet : clinic.getAllVeterinarians()) {
            double total = operationsController
                    .calculateTotalCollectedByVeterinarian(vet.getEmployeeId());
            System.out.println("ID: " + vet.getEmployeeId()
                    + " | Dr(a). " + vet.getFirstName() + " " + vet.getLastName()
                    + " (" + vet.getSpecialty() + ")"
                    + " | Total cobrado: $" + total);
        }
    }

    /**
     * Opción 10: Da de baja una mascota seleccionada por el usuario.
     */
    public void displayRemovePet() {
        System.out.println("=== Dar de baja una mascota ===");
        if (!showAvailablePets()) {
            return;
        }
        try {
            int petId = InputHelper.readInt(scanner, "ID de la mascota a dar de baja");
            Pet pet = operationsController.findPetById(petId);
            String petName = pet.getName();
            operationsController.removePet(petId);
            System.out.println("La mascota '" + petName + "' (ID: " + petId
                    + ") y su historial clínico fueron dados de baja exitosamente.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /**
     * Muestra en consola las citas que tienen estado COMPLETED.
     */
    private boolean showCompletedAppointments() {
        boolean found = false;
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
                System.out.println(appointment.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay citas atendidas disponibles.");
        }
        return found;
    }

    /**
     * Muestra la lista básica de mascotas registradas para facilitar su selección.
     */
    private boolean showAvailablePets() {
        List<Pet> pets = operationsController.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return false;
        }
        for (Pet pet : pets) {
            System.out.println(pet.getId() + " - " + pet.getName()
                    + " (Dueño: " + pet.getOwner().getFirstName() + " "
                    + pet.getOwner().getLastName() + ")");
        }
        return true;
    }

    /**
     * Imprime las citas del historial clínico y el total cobrado a la mascota.
     */
    private void printHistoryRecords(Pet pet, Set<Appointment> history) {
        System.out.println("Historial clínico de: " + pet.getName());
        if (history.isEmpty()) {
            System.out.println("La mascota aún no tiene consultas atendidas en su historial.");
            return;
        }
        for (Appointment appointment : history) {
            System.out.println(" - " + appointment.toString());
        }
        double totalPet = operationsController.calculateTotalCollectedByPet(pet.getId());
        System.out.println("Total histórico cobrado a " + pet.getName() + ": $" + totalPet);
    }
}