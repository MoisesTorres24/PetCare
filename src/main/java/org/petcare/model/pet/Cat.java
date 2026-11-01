package org.petcare.model.pet;

import org.petcare.model.person.Client;

/**
 * Mascota de especie felina.
 */
public class Cat extends Pet {
    /**
     * Indica si el gato es doméstico.
     */
    private boolean isDomestic;

    /**
     * Crea un gato con su indicador de domesticidad.
     */
    public Cat(int id, String name, int age, double weight, Client owner, boolean isDomestic) {
        super(id, name, age, weight, owner);
        this.isDomestic = isDomestic;
    }

    @Override
    /** Emite el sonido del gato. */
    public void makeSound() {

    }

    /**
     * Indica si el gato está catalogado como doméstico.
     */
    public boolean isDomestic() {
        return isDomestic;
    }

    /**
     * Actualiza la clasificación de domesticidad.
     */
    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }
}
