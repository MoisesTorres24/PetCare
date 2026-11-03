package org.petcare.model.pet;

import org.petcare.model.clinic.MedicalHistory;
import org.petcare.model.person.Client;

import org.petcare.util.Validatable;

/**
 * Base de las mascotas registradas en la clínica.
 */
public abstract class Pet {
    /**
     * Identificador interno de la mascota.
     */
    private int id;
    /**
     * Nombre de la mascota.
     */
    private String name;
    /**
     * Edad expresada en años.
     */
    private int age;
    /**
     * Peso de la mascota.
     */
    private double weight;
    /**
     * Cliente responsable de la mascota.
     */
    private Client owner;
    /**
     * Historial clínico asociado.
     */
    private final MedicalHistory history;

    /**
     * Inicializa los datos básicos y crea el historial clínico.
     */
    protected Pet(int id, String name, int age, double weight, Client owner) {
        this.id = id;
        setName(name);
        setAge(age);
        setWeight(weight);
        setOwner(owner);
        this.history = new MedicalHistory();
    }

    /**
     * Emite el sonido característico de la especie.
     */
    public abstract void makeSound();

    /**
     * Obtiene el identificador de la mascota.
     */
    public int getId() {
        return id;
    }

    /**
     * Actualiza el identificador de la mascota.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la mascota.
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre validando que sea obligatorio.
     */
    public void setName(String name) {
        Validatable.requiredText(name, "nombre");
        this.name = name;
    }

    /**
     * Obtiene la edad en años.
     */
    public int getAge() {
        return age;
    }

    /**
     * Actualiza la edad y exige un valor positivo.
     */
    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("la edad debe ser mayor que cero");
        }
        this.age = age;
    }

    /**
     * Obtiene el peso.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Actualiza el peso y exige un valor finito positivo.
     */
    public void setWeight(double weight) {
        if (Double.isNaN(weight) || Double.isInfinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("el peso debe ser mayor que cero");
        }
        this.weight = weight;
    }

    /**
     * Obtiene el cliente responsable.
     */
    public Client getOwner() {
        return owner;
    }

    /**
     * Cambia el responsable y rechaza valores nulos.
     */
    public void setOwner(Client owner) {
        if (owner == null) {
            throw new IllegalArgumentException("el dueño no puede ser nulo");
        }
        this.owner = owner;
    }

    /**
     * Obtiene el historial clínico de la mascota.
     */
    public MedicalHistory getHistory() {
        return history;
    }
}
