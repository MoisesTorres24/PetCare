package org.petcare.view;

import org.petcare.controller.AppointmentController;
import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.model.service.GeneralConsultation;
import org.petcare.model.service.Service;
import org.petcare.model.service.Surgery;
import org.petcare.model.service.SurgeryComplexity;
import org.petcare.model.service.Vaccination;
import org.petcare.model.service.VaccineType;
import org.petcare.util.InputHelper;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/** Vista de consola para agendar y gestionar citas. */
public class AppointmentView {
    private final AppointmentController appointmentController;
    private final Clinic clinic;
    private final Scanner scanner;

    /**
     * Crea la vista de citas.
     *
     * @param appointmentController controlador de citas
     * @param clinic clínica con los datos disponibles
     * @param scanner lector de consola
     */
    public AppointmentView(AppointmentController appointmentController,
                           Clinic clinic, Scanner scanner) {
        this.appointmentController = appointmentController;
        this.clinic = clinic;
        this.scanner = scanner;
    }

    /** Muestra los datos y agenda una cita. */
    public void displayScheduleAppointment() {
        if (!hasRequiredData()) {
            return;
        }
        try {
            Pet pet = selectPet();
            Veterinarian vet = selectVeterinarian();
            LocalDateTime dateTime = readDateTime();
            List<Service> services = buildServices();
            Appointment appointment = appointmentController.scheduleAppointment(
                    pet, vet, dateTime, services);
            System.out.println("Cita agendada con ID: " + appointment.getId());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /** Solicita una cita y cambia su estado a atendida o cancelada. */
    public void displayManageAppointment() {
        try {
            if (!showAppointments()) {
                return;
            }
            int id = InputHelper.readInt(scanner, "ID de la cita");
            System.out.println("¿Qué deseas hacer con la cita?");
            System.out.println("1. Atender cita");
            System.out.println("2. Cancelar cita");
            int option = InputHelper.readOption(scanner, 2);
            AppointmentStatus status = option == 1
                    ? AppointmentStatus.COMPLETED : AppointmentStatus.CANCELED;
            appointmentController.changeAppointmentStatus(id, status);
            System.out.println(option == 1
                    ? "La cita fue marcada como atendida."
                    : "La cita fue cancelada.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private boolean showAppointments() {
        if (clinic.getAppointments().isEmpty()) {
            System.out.println("No hay citas registradas.");
            return false;
        }
        System.out.println("Citas registradas:");
        for (Appointment appointment : clinic.getAppointments()) {
            System.out.println("ID: " + appointment.getId()
                    + " | Mascota: " + appointment.getPet().getName()
                    + " | Veterinario: " + appointment.getVeterinarian().getFirstName()
                    + " " + appointment.getVeterinarian().getLastName()
                    + " | Fecha: " + appointment.getDateTime()
                    + " | Estado: " + appointment.getStatus());
        }
        return true;
    }

    private boolean hasRequiredData() {
        if (clinic.getAllVeterinarians().isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
            return false;
        }
        if (clinic.getAllPets().isEmpty()) {
            System.out.println("No hay clientes con mascotas registradas.");
            return false;
        }
        showAvailableData();
        return true;
    }

    private void showAvailableData() {
        System.out.println("Mascotas:");
        for (Client client : clinic.getAllClients()) {
            for (Pet pet : client.getPets()) {
                System.out.println(pet.getId() + " - " + pet.getName());
            }
        }
        System.out.println("Veterinarios:");
        for (Veterinarian vet : clinic.getAllVeterinarians()) {
            System.out.println(vet.getEmployeeId() + " - " + vet.getFirstName()
                    + " " + vet.getLastName() + " - Consultorio "
                    + vet.getConsultingRoom().getRoomNumber());
        }
    }

    private Pet selectPet() {
        int id = InputHelper.readInt(scanner, "ID de la mascota");
        for (Pet pet : clinic.getAllPets()) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        throw new IllegalArgumentException("No existe una mascota con ese ID");
    }

    private Veterinarian selectVeterinarian() {
        int id = InputHelper.readInt(scanner, "ID del veterinario");
        for (Veterinarian vet : clinic.getAllVeterinarians()) {
            if (vet.getEmployeeId() == id) {
                return vet;
            }
        }
        throw new IllegalArgumentException("No existe un veterinario con ese ID");
    }

    private LocalDateTime readDateTime() {
        try {
            int year = InputHelper.readInt(scanner, "Año");
            int month = InputHelper.readInt(scanner, "Mes");
            int day = InputHelper.readInt(scanner, "Día");
            int hour = InputHelper.readInt(scanner, "Hora");
            int minute = InputHelper.readInt(scanner, "Minutos");
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException exception) {
            throw new IllegalArgumentException("La fecha u hora no es válida");
        }
    }

    private List<Service> buildServices() {
        List<Service> services = new ArrayList<>();
        double total = 0;
        Service service = new GeneralConsultation();
        services.add(service);
        total += service.calculateCost();
        System.out.println("Consulta general agregada.");
        String answer = InputHelper.readText(scanner, "¿Quieres agregar una vacuna? (s/n)");
        if (answer.trim().equalsIgnoreCase("s")) {
            System.out.println("Vacuna: 1) Rabia, 2) Parvovirus");
            VaccineType type = InputHelper.readOption(scanner, 2) == 1
                    ? VaccineType.RABIES : VaccineType.PARVOVIRUS;
            service = new Vaccination(type);
            services.add(service);
            total += service.calculateCost();
        }
        answer = InputHelper.readText(scanner, "¿Quieres agregar una cirugía? (s/n)");
        if (answer.trim().equalsIgnoreCase("s")) {
            System.out.println("Complejidad: 1) Baja, 2) Media, 3) Alta");
            SurgeryComplexity complexity = SurgeryComplexity.values()[
                    InputHelper.readOption(scanner, 3) - 1];
            service = new Surgery(InputHelper.readDecimal(scanner, "Horas"), complexity);
            services.add(service);
            total += service.calculateCost();
        }
        System.out.println("Total de la cita: " + total);
        return services;
    }
}
