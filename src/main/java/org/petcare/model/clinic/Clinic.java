package org.petcare.model.clinic;

import org.petcare.model.person.Employee;

/** Datos básicos de una clínica veterinaria. */
public class Clinic {
    /** Nombre comercial de la clínica. */
    private String name;
    /** Dirección física de la clínica. */
    private String address;

    /** Crea una clínica con nombre y dirección. */
    public Clinic(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /** Registra un empleado en la clínica cuando se implemente su almacenamiento. */
    public void addEmployee(Employee employee) {

    }

    /** Registra un consultorio cuando se implemente su almacenamiento. */
    public void addRoom(ConsultingRoom room) {

    }

    /** Obtiene el nombre de la clínica. */
    public String getName() {
        return name;
    }

    /** Actualiza el nombre de la clínica. */
    public void setName(String name) {
        this.name = name;
    }

    /** Obtiene la dirección de la clínica. */
    public String getAddress() {
        return address;
    }

    /** Actualiza la dirección de la clínica. */
    public void setAddress(String address) {
        this.address = address;
    }
}
