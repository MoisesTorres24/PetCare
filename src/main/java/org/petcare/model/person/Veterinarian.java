package org.petcare.model.person;

import org.petcare.model.clinic.ConsultingRoom;
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
    /** Consultorio fijo asignado para atender citas. */
    private ConsultingRoom consultingRoom;

    /**
     * Crea un veterinario con licencia y especialidad.
     */
    public Veterinarian(int id, String firstName, String lastName, String phone, String email,
                        int employeeId, String medicalLicense, String specialty) {
        super(id, firstName, lastName, phone, email, employeeId,
                EmployeeRole.VETERINARIAN, 25000.0);
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

    /** @return consultorio asignado al veterinario. */
    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    /**
     * Asigna el consultorio fijo del veterinario.
     *
     * @param consultingRoom consultorio asignado
     */
    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        if (consultingRoom == null) {
            throw new IllegalArgumentException("El consultorio no puede ser nulo");
        }
        this.consultingRoom = consultingRoom;
    }
}
