package org.petcare.model.person;

public class Veterinarian extends Employee {
    private String medicalLicense;
    private String specialty;

    public Veterinarian(int id, String firstName, String lastName, String phone, String email, int employeeId, EmployeeRole role, double baseSalary, String medicalLicense, String specialty) {
        super(id, firstName, lastName, phone, email, employeeId, role, baseSalary);
        this.medicalLicense = medicalLicense;
        this.specialty = specialty;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
