package org.petcare.model.service;

/**
 * Contrato para todos los elementos y servicios facturables de la clínica.
 */
public interface Billable {
    /**
     * Calcula el costo total del servicio.
     *
     * @return monto a cobrar (nunca negativo)
     */
    double calculateCost();
}