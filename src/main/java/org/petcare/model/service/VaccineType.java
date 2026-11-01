package org.petcare.model.service;

public enum VaccineType {
    RABIES(250.0),
    PARVOVIRUS(300.0);
    private double cost;

    VaccineType(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
