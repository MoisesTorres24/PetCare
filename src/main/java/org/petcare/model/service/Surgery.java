package org.petcare.model.service;

import java.util.Objects;

import org.petcare.util.Validatable;

/**
 * Servicio quirúrgico cuyo costo depende de horas y complejidad.
 */
public class Surgery extends Service {
    /**
     * Tarifa fija por hora de cirugía.
     */
    private static final double HOURLY_RATE = 800.0;

    /**
     * Duración de la cirugía en horas.
     */
    private double hours;
    /**
     * Nivel de complejidad y riesgo.
     */
    private SurgeryComplexity complexityLevel;

    /**
     * Crea una cirugía con duración y complejidad.
     */
    public Surgery(double hours, SurgeryComplexity complexityLevel) {
        super("Cirugía");
        setHours(hours);
        this.complexityLevel = Objects.requireNonNull(complexityLevel, "El nivel de complejidad no puede ser nulo");
    }

    @Override
    /** Calcula el costo multiplicando horas, tarifa y riesgo. */
    public double calculateCost() {
        return hours * HOURLY_RATE * complexityLevel.getRiskMultiplier();
    }


    /**
     * Obtiene la duración de la cirugía.
     */
    public double getHours() {
        return hours;
    }

    /**
     * Actualiza la duración validando que no sea negativa.
     */
    public void setHours(double hours) {
        Validatable.nonNegativeDecimal(hours, "horas");
        this.hours = hours;
    }

    /**
     * Obtiene la complejidad de la cirugía.
     */
    public SurgeryComplexity getComplexityLevel() {
        return complexityLevel;
    }

    /**
     * Actualiza la complejidad y rechaza valores nulos.
     */
    public void setComplexityLevel(SurgeryComplexity complexityLevel) {
        this.complexityLevel = Objects.requireNonNull(complexityLevel, "El nivel de complejidad no puede ser nulo");
    }
}
