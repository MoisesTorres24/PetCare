package org.petcare.model.clinic;

import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.model.service.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una cita programada para una fecha y hora concretas.
 */
public class Appointment implements Comparable<Appointment> {
    private int id;
    private Pet pet;
    private Veterinarian veterinarian;
    private List<Service> services;
    private ConsultingRoom room;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

    /**
     * Crea una cita con sus recursos y fecha de atención.
     *
     * @param id           identificador de la cita
     * @param pet          mascota que recibirá la atención
     * @param veterinarian veterinario asignado
     * @param room         consultorio reservado
     * @param dateTime     fecha y hora de la cita
     * @param services     lista de servicios solicitados
     */
    public Appointment(int id, Pet pet, Veterinarian veterinarian,
                       ConsultingRoom room, LocalDateTime dateTime, List<Service> services) {
        this.id = id;
        this.pet = pet;
        this.veterinarian = veterinarian;
        this.room = room;
        this.dateTime = dateTime;
        this.services = new ArrayList<>(services);
        this.status = AppointmentStatus.SCHEDULED;
    }

    public int getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    /**
     * Actualiza la mascota asociada a la cita.
     *
     * @param pet nueva mascota
     */
    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        this.pet = pet;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    /**
     * Actualiza el veterinario asignado a la cita.
     *
     * @param veterinarian nuevo veterinario
     */
    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        this.veterinarian = veterinarian;
    }

    public ConsultingRoom getRoom() {
        return room;
    }

    public List<Service> getServices() {
        return Collections.unmodifiableList(services);
    }

    /**
     * Actualiza los servicios de la cita.
     *
     * @param services nuevos servicios
     */
    public void setServices(List<Service> services) {
        if (services == null || services.isEmpty()) {
            throw new IllegalArgumentException("La cita debe tener al menos un servicio");
        }
        this.services = new ArrayList<>(services);
    }

    /**
     * Actualiza el consultorio reservado.
     *
     * @param room nuevo consultorio
     */
    public void setRoom(ConsultingRoom room) {
        if (room == null) {
            throw new IllegalArgumentException("El consultorio no puede ser nulo");
        }
        this.room = room;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Actualiza la fecha y hora de la cita.
     *
     * @param dateTime nueva fecha y hora
     */
    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("La fecha y hora no pueden ser nulas");
        }
        this.dateTime = dateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    /**
     * Actualiza el estado de la cita validando las reglas de negocio.
     *
     * @param status nuevo estado
     * @throws IllegalArgumentException si la transición de estado es inválida
     */
    public void setStatus(AppointmentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        // Regla de negocio: una cita cancelada no puede marcarse como atendida
        if (this.status == AppointmentStatus.CANCELED
                && status == AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException("Una cita cancelada no puede ser atendida");
        }
        // Protegemos también que una cita atendida no se cancele después
        if (this.status == AppointmentStatus.COMPLETED
                && status == AppointmentStatus.CANCELED) {
            throw new IllegalArgumentException("Una cita ya atendida no puede cancelarse");
        }
        this.status = status;
        // Si la cita se atiende, se guarda en el historial clínico de la mascota
        if (status == AppointmentStatus.COMPLETED && this.pet != null) {
            this.pet.getHistory().addRecord(this);
        }
    }

    @Override
    /**
     * Compara dos citas por fecha y hora para mantener el orden en TreeSet.
     *
     * @param other otra cita a comparar
     * @return valor negativo, cero o positivo según el orden cronológico
     */
    public int compareTo(Appointment other) {
        int dateComparison = this.dateTime.compareTo(other.dateTime);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return Integer.compare(this.id, other.id);
    }

    @Override
    /** Devuelve un resumen legible de la cita. */
    public String toString() {
        return "Cita ID: " + id
                + " | Fecha: " + dateTime
                + " | Mascota: " + pet.getName()
                + " | Veterinario: " + veterinarian.getFirstName() + " " + veterinarian.getLastName()
                + " | Consultorio: " + room.getRoomNumber()
                + " | Estado: " + status;
    }
}