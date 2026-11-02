package org.petcare.model.clinic;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Historial de citas y atenciones asociadas a una mascota.
 */
public class MedicalHistory {
    /**
     * Citas ordenadas para facilitar la consulta cronológica.
     */
    private TreeSet<Appointment> appointments = new TreeSet<>();

    /**
     * Añade una cita al historial clínico validando que no sea nula.
     *
     * @param appointment cita a registrar en el historial
     * @throws IllegalArgumentException si la cita es nula
     */
    public void addRecord(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        // TreeSet inserta la cita ordenada por fecha y evita duplicados
        appointments.add(appointment);
    }

    /**
     * Obtiene las citas registradas en el historial en orden cronológico.
     *
     * @return conjunto inmodificable de citas del historial
     */
    public Set<Appointment> getRecords() {
        return Collections.unmodifiableSet(appointments);
    }
}