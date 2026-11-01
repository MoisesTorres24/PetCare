package org.petcare.model.pet;

import org.petcare.model.clinic.MedicalHistory;
import org.petcare.model.person.Client;

import java.util.Objects;

public abstract class Pet {
    private int id;
    private String name;
    private int age;
    private double weight;
    private Client owner;
    private final MedicalHistory history;

    protected Pet(int id, String name, int age, double weight, Client owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.owner = Objects.requireNonNull(owner, "El dueño no puede ser nulo");
        this.history = new MedicalHistory();
    }

    public abstract void makeSound();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public MedicalHistory getHistory() {
        return history;
    }
}
