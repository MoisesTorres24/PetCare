package org.petcare.model.clinic;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class Appointment {
    private LocalDateTime dateTime;

    public Appointment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

//    public Receipt attend() {
//
//    }

    public void cancel() {
    }
    
}
