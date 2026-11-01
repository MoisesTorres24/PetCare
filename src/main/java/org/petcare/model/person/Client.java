package org.petcare.model.person;

import org.petcare.model.pet.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends Person {
    private String address;
    private final List<Pet> pets = new ArrayList<>();

    public Client(int id, String firstName, String lastName, String phone, String email, String address) {
        super(id, firstName, lastName, phone, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void registerPet(Pet pet) {
        Objects.requireNonNull(pet, "La mascota no puede ser nula");
        if (!pets.contains(pet)) {
            pets.add(pet);
        }
    }
}
