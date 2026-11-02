package org.petcare.model.person;

import java.util.Objects;

/**
 * Empleado responsable de la atención en recepción.
 */
public class Receptionist extends Employee {
    /**
     * Turno asignado a la persona recepcionista.
     */
    private ShiftType assignedShift;

    /**
     * Crea una recepcionista con su turno de trabajo.
     */
    public Receptionist(int id, String firstName, String lastName, String phone, String email,
                        int employeeId, ShiftType assignedShift) {
        super(id, firstName, lastName, phone, email, employeeId,
                EmployeeRole.RECEPTIONIST, 12000.0);
        this.assignedShift = Objects.requireNonNull(assignedShift, "El turno no puede ser nulo");
    }

    /**
     * Obtiene el turno asignado.
     */
    public ShiftType getAssignedShift() {
        return assignedShift;
    }

    /**
     * Cambia el turno asignado y rechaza valores nulos.
     */
    public void setAssignedShift(ShiftType assignedShift) {
        this.assignedShift = Objects.requireNonNull(assignedShift, "El turno no puede ser nulo");
    }
}
