package org.petcare.model.clinic;

import org.petcare.model.person.Employee;

public class Clinic {
    private String name;
    private String address;

    public Clinic(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addEmployee(Employee employee) {

    }

    public void addRoom(ConsultingRoom room) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
