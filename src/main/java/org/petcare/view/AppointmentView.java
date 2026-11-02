package org.petcare.view;

import org.petcare.controller.AppointmentController;
import org.petcare.controller.RegistrationController;
import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.ConsultingRoom;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.util.InputHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Vista de consola para agendar y gestionar citas. */
public class AppointmentView {
    private final AppointmentController appointmentController;
    private final RegistrationController registrationController;
    private final Scanner scanner;

    /**
     * Crea la vista con sus controladores y entrada de consola.
     *
     * @param appointmentController controlador de citas
     * @param registrationController controlador de registros
     * @param scanner lector de consola
     */
    public AppointmentView(AppointmentController appointmentController,
                           RegistrationController registrationController, Scanner scanner) {
        this.appointmentController = appointmentController;
        this.registrationController = registrationController;
        this.scanner = scanner;
    }

    /** Muestra los datos requeridos y agenda una cita. */
    public void displayScheduleAppointment() {
        try {
            showAvailableData();
            Pet pet = selectPet();
            Veterinarian vet = selectVeterinarian();
            ConsultingRoom room = selectRoom();
            LocalDateTime date = readDate();
            Appointment appointment = appointmentController.scheduleAppointment(pet, vet, room, date);
            System.out.println("Cita agendada exitosamente con ID: " + appointment.getId());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /** Solicita una cita y cambia su estado a atendida o cancelada. */
    public void displayManageAppointment() {
        try {
            int appointmentId = InputHelper.readInt(scanner, "ID de la cita");
            int option = InputHelper.readOption(scanner, 2);
            AppointmentStatus status = option == 1
                    ? AppointmentStatus.COMPLETED : AppointmentStatus.CANCELED;
            appointmentController.changeAppointmentStatus(appointmentId, status);
            System.out.println("Estado de la cita actualizado correctamente.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private void showAvailableData() {
        System.out.println("Mascotas:");
        for (Client client : registrationController.getAllClients()) {
            for (Pet pet : client.getPets()) {
                System.out.println(pet.getId() + " - " + pet.getName());
            }
        }
        System.out.println("Veterinarios:");
        for (Veterinarian vet : appointmentController.getAllVeterinarians()) {
            System.out.println(vet.getEmployeeId() + " - " + vet.getFirstName() + " " + vet.getLastName());
        }
        System.out.println("Consultorios:");
        for (ConsultingRoom room : appointmentController.getAllRooms()) {
            System.out.println(room.getRoomNumber());
        }
    }

    private Pet selectPet() {
        int id = InputHelper.readInt(scanner, "ID de la mascota");
        for (Client client : registrationController.getAllClients()) {
            for (Pet pet : client.getPets()) {
                if (pet.getId() == id) {
                    return pet;
                }
            }
        }
        throw new IllegalArgumentException("No existe una mascota con ese ID");
    }

    private Veterinarian selectVeterinarian() {
        int id = InputHelper.readInt(scanner, "ID del veterinario");
        for (Veterinarian vet : appointmentController.getAllVeterinarians()) {
            if (vet.getEmployeeId() == id) {
                return vet;
            }
        }
        throw new IllegalArgumentException("No existe un veterinario con ese ID");
    }

    private ConsultingRoom selectRoom() {
        String id = String.valueOf(InputHelper.readInt(scanner, "ID del consultorio"));
        for (ConsultingRoom room : appointmentController.getAllRooms()) {
            if (room.getRoomNumber().equals(id)) {
                return room;
            }
        }
        throw new IllegalArgumentException("No existe un consultorio con ese ID");
    }

    private LocalDateTime readDate() {
        String text = InputHelper.readText(scanner, "Fecha y hora (AAAA-MM-DDTHH:MM)");
        try {
            return LocalDateTime.parse(text);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("La fecha debe usar el formato AAAA-MM-DDTHH:MM");
        }
    }
}
