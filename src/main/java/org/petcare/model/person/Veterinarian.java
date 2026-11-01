package org.petcare.model.person;

import org.petcare.util.Validatable;

/**
 * Empleado especializado en la atención médica veterinaria.
 */
public class Veterinarian extends Employee {
    /**
     * Cédula o licencia profesional.
     */
    private String medicalLicense;
    /**
     * Área de especialidad médica.
     */
    private String specialty;

    /**
     * Crea un veterinario con licencia y especialidad.
     */
    public Veterinarian(int id, String firstName, String lastName, String phone, String email, int employeeId, EmployeeRole role, double baseSalary, String medicalLicense, String specialty) {
        super(id, firstName, lastName, phone, email, employeeId, role, baseSalary);
        setMedicalLicense(medicalLicense);
        setSpecialty(specialty);
    }

    /**
     * Obtiene la licencia profesional.
     */
    public String getMedicalLicense() {
        return medicalLicense;
    }

    /**
     * Actualiza la licencia tras validar que sea obligatoria.
     */
    public void setMedicalLicense(String medicalLicense) {
        Validatable.requiredText(medicalLicense, "cédula profesional");
        this.medicalLicense = medicalLicense;
    }

    /**
     * Obtiene la especialidad médica.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Actualiza la especialidad tras validar que sea obligatoria.
     */
    public void setSpecialty(String specialty) {
        Validatable.requiredText(specialty, "especialidad");
        this.specialty = specialty;
    }
}
