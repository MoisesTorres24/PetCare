package org.petcare.model.pet;

import org.petcare.model.person.Client;

public class Cat extends Pet {
    private boolean isDomestic;

    public Cat(int id, String name, int age, double weight, Client owner, boolean isDomestic) {
        super(id, name, age, weight, owner);
        this.isDomestic = isDomestic;
    }

    @Override
    public void makeSound() {

    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }
}
