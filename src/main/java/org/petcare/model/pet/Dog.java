package org.petcare.model.pet;

import org.petcare.model.person.Client;
import org.petcare.util.Validatable;

/**
 * Mascota de especie canina.
 */
public class Dog extends Pet {
    /**
     * Raza del perro.
     */
    private String breed;
    /**
     * Nivel de energía del perro.
     */
    private String energyLevel;

    /**
     * Crea un perro con raza y nivel de energía.
     */
    public Dog(int id, String name, int age, double weight, Client owner, String breed, String energyLevel) {
        super(id, name, age, weight, owner);
        setBreed(breed);
        setEnergyLevel(energyLevel);
    }

    /** Emite el sonido del perro. */
    @Override
    public void makeSound() {
        // Imprime el ladrido característico del perro
        System.out.println("¡Guau, guau!");
    }

    /**
     * Obtiene la raza.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Actualiza la raza validando que sea obligatoria.
     */
    public void setBreed(String breed) {
        Validatable.requiredText(breed, "raza");
        this.breed = breed;
    }

    /**
     * Obtiene el nivel de energía.
     */
    public String getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Actualiza el nivel de energía validando que sea obligatorio.
     */
    public void setEnergyLevel(String energyLevel) {
        Validatable.requiredText(energyLevel, "nivel de energía");
        this.energyLevel = energyLevel;
    }

    /** Devuelve la representación en texto de los datos del perro. */
    @Override
    public String toString() {
        return "ID: " + getId() + " | [Perro] Nombre: " + getName()
                + " | Edad: " + getAge() + " años | Peso: " + getWeight()
                + " kg | Raza: " + breed + " | Energía: " + energyLevel
                + " | Dueño: " + getOwner().getFirstName() + " " + getOwner().getLastName();
    }
}