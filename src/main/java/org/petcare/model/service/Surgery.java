package org.petcare.model.service;

import java.util.Objects;

public class Surgery extends Service {
    private static final double HOURLY_RATE = 800.0;

    private double hours;
    private SurgeryComplexity complexityLevel;

    public Surgery(double hours, SurgeryComplexity complexityLevel) {
        super("Cirugía");
        this.hours = hours;
        this.complexityLevel = Objects.requireNonNull(complexityLevel, "El nivel de complejidad no puede ser nulo");
    }

    @Override
    public double calculateCost() {
        return hours * HOURLY_RATE * complexityLevel.getRiskMultiplier();
    }


    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public SurgeryComplexity getComplexityLevel() {
        return complexityLevel;
    }

    public void setComplexityLevel(SurgeryComplexity complexityLevel) {
        this.complexityLevel = Objects.requireNonNull(complexityLevel, "El nivel de complejidad no puede ser nulo");
    }
}
