package org.petcare.model.pet;

import org.petcare.model.person.Client;
import org.petcare.util.Validatable;

/**
 * Mascota de especie de ave exótica.
 */
public class ExoticBird extends Pet {
    /**
     * Envergadura de las alas.
     */
    private double wingSpan;

    /**
     * Crea un ave exótica con su envergadura.
     */
    public ExoticBird(int id, String name, int age, double weight, Client owner, double wingSpan) {
        super(id, name, age, weight, owner);
        setWingSpan(wingSpan);
    }

    /**
     * Obtiene la envergadura de las alas.
     */
    public double getWingSpan() {
        return wingSpan;
    }

    /**
     * Actualiza la envergadura y valida que no sea negativa.
     */
    public void setWingSpan(double wingSpan) {
        Validatable.nonNegativeDecimal(wingSpan, "envergadura");
        this.wingSpan = wingSpan;
    }

    @Override
    /** Emite el sonido del ave exótica. */
    public void makeSound() {
        // Imprime el canto característico del ave exótica
        System.out.println("¡Pío, pío!");
    }

    @Override
    /** Devuelve la representación en texto de los datos del ave exótica. */
    public String toString() {
        return "ID: " + getId() + " | [Ave Exótica] Nombre: " + getName()
                + " | Edad: " + getAge() + " años | Peso: " + getWeight()
                + " kg | Envergadura: " + wingSpan + " cm"
                + " | Dueño: " + getOwner().getFirstName() + " " + getOwner().getLastName();
    }
}