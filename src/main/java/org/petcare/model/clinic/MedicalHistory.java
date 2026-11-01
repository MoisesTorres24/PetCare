package org.petcare.model.clinic;

import java.util.TreeSet;

/** Historial de citas y atenciones asociadas a una mascota. */
public class MedicalHistory {
    /** Citas ordenadas para facilitar la consulta cronológica. */
    private TreeSet<Appointment> appointments = new TreeSet<>();

    /** Añade una cita al historial cuando se complete su persistencia. */
    public void addRecord(Appointment appointment){

    }

//    public Appointment getLastVisit(){
//        return Appointment();
//    }
}
