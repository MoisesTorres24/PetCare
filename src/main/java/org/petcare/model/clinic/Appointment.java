package org.petcare.model.clinic;

import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;

import java.time.LocalDateTime;

/** Representa una cita programada para una fecha y hora concretas. */
public class Appointment {
    private int id;
    private Pet pet;
    private Veterinarian veterinarian;
    private ConsultingRoom room;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

    /**
     * Crea una cita con sus recursos y fecha de atención.
     *
     * @param id identificador de la cita
     * @param pet mascota que recibirá la atención
     * @param veterinarian veterinario asignado
     * @param room consultorio reservado
     * @param dateTime fecha y hora de la cita
     */
    public Appointment(int id, Pet pet, Veterinarian veterinarian,
                       ConsultingRoom room, LocalDateTime dateTime) {
        this.id = id;
        this.pet = pet;
        this.veterinarian = veterinarian;
        this.room = room;
        this.dateTime = dateTime;
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
        this.veterinarian = veterinarian;
    }

    public ConsultingRoom getRoom() {
        return room;
    }

    /**
     * Actualiza el consultorio reservado.
     *
     * @param room nuevo consultorio
     */
    public void setRoom(ConsultingRoom room) {
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
        this.dateTime = dateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    /**
     * Actualiza el estado de la cita.
     *
     * @param status nuevo estado
     */
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
