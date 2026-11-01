package org.petcare.model.pet;

import org.petcare.model.person.Client;

public class ExoticBird extends Pet {
    private double wingSpan;

    public ExoticBird(int id, String name, int age, double weight, Client owner, double wingSpan) {
        super(id, name, age, weight, owner);
        this.wingSpan = wingSpan;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
    }

    @Override
    public void makeSound() {

    }
}
