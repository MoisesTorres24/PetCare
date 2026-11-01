package org.petcare.model.clinic;


import java.time.LocalDate;
import java.time.LocalDateTime;


/** Representa una cita programada para una fecha y hora concretas. */
public class Appointment {
    /** Fecha y hora de la cita. */
    private LocalDateTime dateTime;

    /** Crea una cita con la fecha y hora indicadas. */
    public Appointment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /** Obtiene la fecha y hora programadas. */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /** Actualiza la fecha y hora de la cita. */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

//    public Receipt attend() {
//
//    }

    /** Cancela la cita; la operación queda preparada para la gestión de estado. */
    public void cancel() {
    }
    
}
