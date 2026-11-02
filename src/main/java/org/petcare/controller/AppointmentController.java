package org.petcare.controller;

import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.model.service.Service;

import java.time.LocalDateTime;
import java.util.List;

/** Coordina el agendamiento y la gestión de citas. */
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
     * Agenda una cita para una mascota, veterinario, fecha y servicio.
     *
     * @param pet mascota que recibirá la atención
     * @param vet veterinario asignado
     * @param dateTime fecha y hora de la cita
     * @param services servicios solicitados
     * @return cita creada
     */
    public Appointment scheduleAppointment(Pet pet, Veterinarian vet,
                                           LocalDateTime dateTime, List<Service> services) {
        validateData(pet, vet, dateTime, services);
        if (!isVetAvailable(vet, dateTime)) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita a esa hora");
        }
        Appointment appointment = new Appointment(nextAppointmentId++, pet, vet,
                vet.getConsultingRoom(), dateTime, services);
        clinic.addAppointment(appointment);
        return appointment;
    }

    /**
     * Cambia el estado de una cita existente.
     *
     * @param id identificador de la cita
     * @param newStatus estado solicitado
     */
    public void changeAppointmentStatus(int id, AppointmentStatus newStatus) {
        Appointment appointment = findAppointment(id);
        validateStatus(appointment, newStatus);
        appointment.setStatus(newStatus);
    }

    private void validateData(Pet pet, Veterinarian vet,
                              LocalDateTime dateTime, List<Service> services) {
        if (pet == null || vet == null || dateTime == null
                || services == null || services.isEmpty()) {
            throw new IllegalArgumentException("Todos los datos de la cita son obligatorios");
        }
        if (vet.getConsultingRoom() == null) {
            throw new IllegalArgumentException("El veterinario no tiene consultorio asignado");
        }
    }

    private boolean isVetAvailable(Veterinarian vet, LocalDateTime dateTime) {
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.SCHEDULED
                    && appointment.getVeterinarian().equals(vet)
                    && appointment.getDateTime().equals(dateTime)) {
                return false;
            }
        }
        return true;
    }

    private Appointment findAppointment(int id) {
        for (Appointment appointment : clinic.getAppointments()) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        throw new IllegalArgumentException("No existe una cita con ese ID");
    }

    private void validateStatus(Appointment appointment, AppointmentStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        if (appointment.getStatus() == AppointmentStatus.CANCELED
                && newStatus == AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException("Una cita cancelada no puede ser atendida");
        }
    }
}
