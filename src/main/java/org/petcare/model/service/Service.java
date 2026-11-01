package org.petcare.model.service;

import org.petcare.util.Validatable;

/**
 * Servicio veterinario facturable.
 */
public abstract class Service {
    /**
     * Nombre visible del servicio.
     */
    private String name;

    /**
     * Crea un servicio con un nombre obligatorio.
     */
    protected Service(String name) {
        setName(name);
    }

    /**
     * Calcula el costo del servicio según sus parámetros.
     */
    public abstract double calculateCost();

    /**
     * Obtiene el nombre del servicio.
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre validando que no esté vacío.
     */
    public void setName(String name) {
        Validatable.requiredText(name, "nombre");
        this.name = name;
    }
}