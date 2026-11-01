package org.petcare.model.pet;

import org.petcare.model.person.Client;

public class Dog extends Pet {
    private String breed;

    public Dog(int id, String name, int age, double weight, Client owner, String breed) {
        super(id, name, age, weight, owner);
        this.breed = breed;
    }

    @Override
    public void makeSound() {

    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
