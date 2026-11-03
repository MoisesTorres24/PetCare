package org.petcare.model.service;

/**
 * Servicio de consulta veterinaria general.
 */
public class GeneralConsultation extends Service {
    private static final double FIXED_FEE = 500.0;
    /**
     * Tarifa base de la consulta.
     */
    private final double baseFee;

    /**
     * Crea una consulta con una tarifa fija.
     */
    public GeneralConsultation() {
        super("Consulta General");
        this.baseFee = FIXED_FEE;
    }

    /**
     * Obtiene la tarifa base.
     */
    public double getBaseFee() {
        return baseFee;
    }

    /** Calcula el costo de la consulta. */
    @Override
    public double calculateCost() {
        return baseFee;
    }
}
