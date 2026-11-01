package org.petcare.model.person;

public abstract class Employee extends Person {
    private int employeeId;
    private EmployeeRole role;
    private double baseSalary;

    protected Employee(int id, String firstName, String lastName, String phone, String email, int employeeId, EmployeeRole role, double baseSalary) {
        super(id, firstName, lastName, phone, email);
        this.employeeId = employeeId;
        this.role = role;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}
