package org.petcare.model.service;

/**
 * Niveles de complejidad usados para ajustar el costo quirúrgico.
 */
public enum SurgeryComplexity {
    LOW(1.0),
    MEDIUM(1.5),
    HIGH(2.5);

    /**
     * Multiplicador aplicado por el nivel de riesgo.
     */
    private final double riskMultiplier;

    /**
     * Crea un nivel con su multiplicador de riesgo.
     */
    SurgeryComplexity(double riskMultiplier) {
        this.riskMultiplier = riskMultiplier;
    }

    /**
     * Obtiene el multiplicador de riesgo.
     */
    public double getRiskMultiplier() {
        return riskMultiplier;
    }
}
