package org.petcare.model.service;

public class GeneralConsultation extends Service {
    private double baseFee;

    public GeneralConsultation(double baseFee) {
        super("Consulta General");
        this.baseFee = baseFee;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    @Override
    public double calculateCost() {
        return 0;
    }
}
