package org.petcare.model.service;

import org.petcare.util.Validatable;

/**
 * Servicio de consulta veterinaria general.
 */
public class GeneralConsultation extends Service {
    /**
     * Tarifa base de la consulta.
     */
    private double baseFee;

    /**
     * Crea una consulta con su tarifa base.
     */
    public GeneralConsultation(double baseFee) {
        super("Consulta General");
        setBaseFee(baseFee);
    }

    /**
     * Obtiene la tarifa base.
     */
    public double getBaseFee() {
        return baseFee;
    }

    /**
     * Actualiza la tarifa base tras validar que no sea negativa.
     */
    public void setBaseFee(double baseFee) {
        Validatable.nonNegativeDecimal(baseFee, "tarifa base");
        this.baseFee = baseFee;
    }

    @Override
    /** Calcula el costo de la consulta. */
    public double calculateCost() {
        return 0;
    }
}
