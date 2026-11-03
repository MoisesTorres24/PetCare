package org.petcare.model.person;

import org.petcare.util.Validatable;

/**
 * Base común para los empleados de la clínica.
 */
public abstract class Employee extends Person {
    /**
     * Identificador laboral del empleado.
     */
    private int employeeId;
    /**
     * Rol desempeñado en la clínica.
     */
    private EmployeeRole role;
    /**
     * Sueldo base validado.
     */
    private double baseSalary;

    /**
     * Inicializa los datos personales y laborales del empleado.
     */
    protected Employee(int id, String firstName, String lastName, String phone, String email, int employeeId, EmployeeRole role, double baseSalary) {
        super(id, firstName, lastName, phone, email);
        this.employeeId = employeeId;
        this.role = role;
        setBaseSalary(baseSalary);
    }

    /** Obtiene el identificador laboral. */
    /** @return identificador laboral del empleado. */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Actualiza el identificador laboral.
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /** Obtiene el rol del empleado. */
    /** @return rol desempeñado por el empleado. */
    public EmployeeRole getRole() {
        return role;
    }

    /**
     * Actualiza el rol del empleado.
     */
    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    /** Obtiene el sueldo base. */
    /** @return sueldo base del empleado. */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Actualiza el sueldo base tras validar que no sea negativo.
     */
    public void setBaseSalary(double baseSalary) {
        Validatable.nonNegativeDecimal(baseSalary, "sueldo base");
        this.baseSalary = baseSalary;
    }
}
