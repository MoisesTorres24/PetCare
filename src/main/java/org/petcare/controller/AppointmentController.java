package org.petcare.controller;

import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.clinic.ConsultingRoom;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;

import java.time.LocalDateTime;
import java.util.Collection;

/** Coordina el agendamiento y la gestión de citas de la clínica. */
public class AppointmentController {
    private final Clinic clinic;
    private int nextAppointmentId = 1;

    /**
     * Crea el controlador asociado a una clínica.
     *
     * @param clinic clínica donde se almacenan las citas
     */
    public AppointmentController(Clinic clinic) {
        if (clinic == null) {
            throw new IllegalArgumentException("La clínica no puede ser nula");
        }
        this.clinic = clinic;
    }

    /**
     * Programa una cita después de validar la disponibilidad.
     *
     * @param pet mascota que recibirá la atención
     * @param vet veterinario asignado
     * @param room consultorio reservado
     * @param date fecha y hora de la cita
     * @return cita creada
     */
    public Appointment scheduleAppointment(Pet pet, Veterinarian vet,
                                           ConsultingRoom room, LocalDateTime date) {
        validateAppointmentData(pet, vet, room, date);
        if (!isVetAvailable(vet, date)) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita a esa hora");
        }
        if (!isRoomAvailable(room, date)) {
            throw new IllegalArgumentException("El consultorio ya está ocupado a esa hora");
        }
        Appointment appointment = new Appointment(nextAppointmentId++, pet, vet, room, date);
        clinic.addAppointment(appointment);
        return appointment;
    }

    /**
     * Cambia el estado de una cita existente.
     *
     * @param appointmentId identificador de la cita
     * @param newStatus nuevo estado solicitado
     */
    public void changeAppointmentStatus(int appointmentId, AppointmentStatus newStatus) {
        Appointment appointment = findAppointment(appointmentId);
        if (newStatus == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        if (appointment.getStatus() == AppointmentStatus.CANCELED
                && newStatus == AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException("Una cita cancelada no puede ser atendida");
        }
        appointment.setStatus(newStatus);
    }

    public Collection<Pet> getAllPets() {
        return clinic.getAllPets();
    }

    public Collection<Veterinarian> getAllVeterinarians() {
        return clinic.getAllVeterinarians();
    }

    public Collection<ConsultingRoom> getAllRooms() {
        return clinic.getAllRooms();
    }

    private void validateAppointmentData(Pet pet, Veterinarian vet,
                                         ConsultingRoom room, LocalDateTime date) {
        if (pet == null || vet == null || room == null || date == null) {
            throw new IllegalArgumentException("Los datos de la cita son obligatorios");
        }
    }

    private boolean isVetAvailable(Veterinarian vet, LocalDateTime date) {
        for (Appointment appointment : clinic.getAllAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.SCHEDULED
                    && appointment.getVeterinarian() == vet
                    && appointment.getDateTime().equals(date)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRoomAvailable(ConsultingRoom room, LocalDateTime date) {
        for (Appointment appointment : clinic.getAllAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.SCHEDULED
                    && appointment.getRoom() == room
                    && appointment.getDateTime().equals(date)) {
                return false;
            }
        }
        return true;
    }

    private Appointment findAppointment(int appointmentId) {
        for (Appointment appointment : clinic.getAllAppointments()) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        throw new IllegalArgumentException("No existe una cita con ese ID");
    }
}
