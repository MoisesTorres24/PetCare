package org.petcare.model.service;

import org.petcare.util.Validatable;

/**
 * Servicio veterinario facturable.
 */
public abstract class Service implements Billable {
    /**
     * Nombre visible del servicio.
     */
    private String name;

    /**
     * Crea un servicio con un nombre obligatorio.
     *
     * @param name nombre del servicio
     */
    protected Service(String name) {
        setName(name);
    }

    /**
     * Calcula el costo final del servicio según sus reglas de negocio.
     *
     * @return costo del servicio
     */
    @Override
    public abstract double calculateCost();

    /**
     * Obtiene el nombre del servicio.
     *
     * @return nombre del servicio
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre validando que no esté vacío.
     *
     * @param name nuevo nombre del servicio
     */
    public void setName(String name) {
        Validatable.requiredText(name, "nombre");
        this.name = name;
    }
}