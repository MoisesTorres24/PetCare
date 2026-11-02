package org.petcare.controller;

import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.clinic.Receipt;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.model.service.Billable;
import org.petcare.model.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controlador para las operaciones de facturación, consulta de historial,
 * listado polimórfico de mascotas y baja del sistema.
 */
public class ClinicOperationsController {
    /**
     * Clínica sobre la cual se ejecutan las operaciones.
     */
    private final Clinic clinic;

    /**
     * Crea el controlador de operaciones de la clínica.
     *
     * @param clinic clínica activa del sistema
     * @throws IllegalArgumentException si la clínica es nula
     */
    public ClinicOperationsController(Clinic clinic) {
        if (clinic == null) {
            throw new IllegalArgumentException("La clínica no puede ser nula");
        }
        this.clinic = clinic;
    }

    /**
     * Genera un comprobante de pago para una cita previamente atendida.
     *
     * @param appointmentId identificador de la cita
     * @return comprobante generado con el total calculado
     * @throws IllegalArgumentException si la cita no existe o no está atendida
     */
    public Receipt generateReceiptForAppointment(int appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        // Validamos que la cita haya sido atendida antes de cobrar
        if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException(
                    "Solo se puede generar comprobante de una cita atendida");
        }
        appointment.getPet().getHistory().addRecord(appointment);
        List<Billable> billables = new ArrayList<>(appointment.getServices());
        double total = calculateBillableTotal(billables);
        return new Receipt(total, LocalDate.now(), appointment.getServices());
    }

    /**
     * Obtiene el historial clínico ordenado de una mascota.
     *
     * @param petId identificador de la mascota
     * @return conjunto ordenado de citas en su historial
     * @throws IllegalArgumentException si la mascota no existe
     */
    public Set<Appointment> getPetMedicalHistory(int petId) {
        Pet pet = findPetById(petId);
        syncCompletedAppointmentsToHistory(pet);
        return pet.getHistory().getRecords();
    }

    /**
     * Devuelve todas las mascotas registradas en una lista polimórfica.
     *
     * @return lista de mascotas de todos los clientes
     */
    public List<Pet> getAllPets() {
        return new ArrayList<>(clinic.getAllPets());
    }

    /**
     * Calcula el monto total cobrado en citas atendidas por un veterinario.
     *
     * @param employeeId identificador laboral del veterinario
     * @return suma total cobrada por el veterinario
     * @throws IllegalArgumentException si el veterinario no existe
     */
    public double calculateTotalCollectedByVeterinarian(int employeeId) {
        Veterinarian vet = findVeterinarianById(employeeId);
        double total = 0.0;
        // Regla 6: Todo cobro puede consultarse por veterinario
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.COMPLETED
                    && appointment.getVeterinarian().equals(vet)) {
                List<Billable> billables = new ArrayList<>(appointment.getServices());
                total += calculateBillableTotal(billables);
            }
        }
        return total;
    }

    /**
     * Calcula el monto total cobrado en citas atendidas para una mascota.
     *
     * @param petId identificador de la mascota
     * @return suma total cobrada a la mascota
     * @throws IllegalArgumentException si la mascota no existe
     */
    public double calculateTotalCollectedByPet(int petId) {
        Pet pet = findPetById(petId);
        double total = 0.0;
        // Regla 6: Todo cobro puede consultarse por mascota
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.COMPLETED
                    && appointment.getPet().equals(pet)) {
                List<Billable> billables = new ArrayList<>(appointment.getServices());
                total += calculateBillableTotal(billables);
            }
        }
        return total;
    }

    /**
     * Da de baja una mascota eliminándola de la lista de su dueño.
     * Al perder su referencia, la mascota y su historial clínico (composición)
     * quedan elegibles para el recolector de basura (Garbage Collector).
     *
     * @param petId identificador de la mascota a eliminar
     * @throws IllegalArgumentException si la mascota no existe
     */
    public void removePet(int petId) {
        Pet pet = findPetById(petId);
        Client owner = pet.getOwner();
        cancelScheduledAppointmentsForPet(pet);
        // Eliminamos la referencia en el dueño; el objeto MedicalHistory interno
        // muere junto con Pet al quedar ambos sin referencias activas en memoria.
        owner.getPets().remove(pet);
    }

    /**
     * Busca una mascota por su identificador dentro de la clínica.
     */
    public Pet findPetById(int petId) {
        for (Pet pet : clinic.getAllPets()) {
            if (pet.getId() == petId) {
                return pet;
            }
        }
        throw new IllegalArgumentException("No existe una mascota con el ID " + petId);
    }

    /**
     * Busca un veterinario por su número de empleado.
     */
    public Veterinarian findVeterinarianById(int employeeId) {
        for (Veterinarian vet : clinic.getAllVeterinarians()) {
            if (vet.getEmployeeId() == employeeId) {
                return vet;
            }
        }
        throw new IllegalArgumentException(
                "No existe un veterinario con el ID " + employeeId);
    }

    /**
     * Busca una cita por su identificador.
     */
    public Appointment findAppointmentById(int appointmentId) {
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        throw new IllegalArgumentException(
                "No existe una cita con el ID " + appointmentId);
    }

    /**
     * Suma polimórficamente el costo de una lista de objetos Billable sin instanceof.
     */
    private double calculateBillableTotal(List<Billable> billables) {
        double total = 0.0;
        for (Billable item : billables) {
            double cost = item.calculateCost();
            if (cost < 0) {
                throw new IllegalArgumentException("El costo no puede ser negativo");
            }
            total += cost;
        }
        return total;
    }

    /**
     * Asegura que todas las citas atendidas de la mascota estén en su historial.
     */
    private void syncCompletedAppointmentsToHistory(Pet pet) {
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getPet().equals(pet)
                    && appointment.getStatus() == AppointmentStatus.COMPLETED) {
                pet.getHistory().addRecord(appointment);
            }
        }
    }

    /**
     * Cancela las citas programadas de una mascota que será dada de baja.
     */
    private void cancelScheduledAppointmentsForPet(Pet pet) {
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getPet().equals(pet)
                    && appointment.getStatus() == AppointmentStatus.SCHEDULED) {
                appointment.setStatus(AppointmentStatus.CANCELED);
            }
        }
    }
}