package org.petcare.model.service;

import java.util.Objects;

public class Vaccination extends Service {
    private double baseFee;
    private VaccineType vaccineType;

    public Vaccination(double baseFee, VaccineType vaccineType) {
        super("Vacunación");
        this.baseFee = baseFee;
        this.vaccineType = Objects.requireNonNull(vaccineType, "El tipo de vacuna no puede ser nulo");
    }

    @Override
    public double calculateCost() {
        return 0;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }
}


