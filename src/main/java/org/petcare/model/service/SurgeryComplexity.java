package org.petcare.model.service;

public enum SurgeryComplexity {
    LOW(1.0),
    MEDIUM(1.5),
    HIGH(2.5);

    private double riskMultiplier;

    SurgeryComplexity(double riskMultiplier) {
        this.riskMultiplier = riskMultiplier;
    }

    public double getRiskMultiplier() {
        return riskMultiplier;
    }
}
