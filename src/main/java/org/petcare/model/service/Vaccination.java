package org.petcare.model.service;

import java.util.Objects;

import org.petcare.util.Validatable;

/**
 * Servicio de vacunación con tarifa y tipo de vacuna.
 */
public class Vaccination extends Service {
    private static final double DEFAULT_APPLICATION_FEE = 100.0;
    /**
     * Tarifa base de aplicación.
     */
    private double baseFee;
    /**
     * Tipo de vacuna aplicada.
     */
    private VaccineType vaccineType;

    /**
     * Crea una vacunación usando la tarifa fija de aplicación.
     *
     * @param vaccineType tipo de vacuna aplicada
     */
    public Vaccination(VaccineType vaccineType) {
        this(DEFAULT_APPLICATION_FEE, vaccineType);
    }

    /**
     * Crea una vacunación con tarifa y tipo de vacuna.
     *
     * @param baseFee     tarifa base de aplicación
     * @param vaccineType tipo de vacuna aplicada
     */
    public Vaccination(double baseFee, VaccineType vaccineType) {
        super("Vacunación");
        setBaseFee(baseFee);
        this.vaccineType = Objects.requireNonNull(
                vaccineType, "El tipo de vacuna no puede ser nulo");
    }

    @Override
    /** Calcula el costo sumando la tarifa base y el costo de la vacuna. */
    public double calculateCost() {
        // Regla de negocio: la vacunación suma el costo de la vacuna a la tarifa base
        return baseFee + vaccineType.getCost();
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
        this.vaccineType = Objects.requireNonNull(
                vaccineType, "El tipo de vacuna no puede ser nulo");
    }
}