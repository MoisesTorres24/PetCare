package org.petcare.model.service;

import java.util.Objects;

import org.petcare.util.Validatable;

/**
 * Servicio de vacunación con tarifa y tipo de vacuna.
 */
public class Vaccination extends Service {
    /**
     * Tarifa base de aplicación.
     */
    private double baseFee;
    /**
     * Tipo de vacuna aplicada.
     */
    private VaccineType vaccineType;

    /**
     * Crea una vacunación con tarifa y tipo de vacuna.
     */
    public Vaccination(double baseFee, VaccineType vaccineType) {
        super("Vacunación");
        setBaseFee(baseFee);
        this.vaccineType = Objects.requireNonNull(vaccineType, "El tipo de vacuna no puede ser nulo");
    }

    @Override
    /** Calcula el costo de la vacunación. */
    public double calculateCost() {
        return 0;
    }

    /**
     * Obtiene la tarifa base.
     */
    public double getBaseFee() {
        return baseFee;
    }

    /**
     * Actualiza la tarifa base tras validarla.
     */
    public void setBaseFee(double baseFee) {
        Validatable.nonNegativeDecimal(baseFee, "tarifa base");
        this.baseFee = baseFee;
    }

    /**
     * Obtiene el tipo de vacuna.
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * Actualiza el tipo de vacuna.
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }
}
