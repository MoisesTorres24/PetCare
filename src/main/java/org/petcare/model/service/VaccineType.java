package org.petcare.model.service;

/**
 * Tipos de vacuna y su costo asociado.
 */
public enum VaccineType {
    RABIES(250.0),
    PARVOVIRUS(300.0);
    /**
     * Costo unitario de la vacuna.
     */
    private final double cost;

    /**
     * Crea un tipo de vacuna con su costo.
     */
    VaccineType(double cost) {
        this.cost = cost;
    }

    /**
     * Obtiene el costo de la vacuna.
     */
    public double getCost() {
        return cost;
    }
}
