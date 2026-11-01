package org.petcare.model.person;

import java.util.Objects;

public class Receptionist extends Employee {
    private ShiftType assignedShift;

    public Receptionist(int id, String firstName, String lastName, String phone, String email, int employeeId, EmployeeRole role, double baseSalary, ShiftType assignedShift) {
        super(id, firstName, lastName, phone, email, employeeId, role, baseSalary);
        this.assignedShift = Objects.requireNonNull(assignedShift, "El turno no puede ser nulo");
    }

    public ShiftType getAssignedShift() {
        return assignedShift;
    }

    public void setAssignedShift(ShiftType assignedShift) {
        this.assignedShift = Objects.requireNonNull(assignedShift, "El turno no puede ser nulo");
    }
}
