package org.petcare.model.person;

import org.petcare.model.pet.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.petcare.util.Validatable;

/**
 * Persona que posee y registra una o más mascotas.
 */
public class Client extends Person {
    /**
     * Domicilio del cliente.
     */
    private String address;
    /**
     * Mascotas asociadas al cliente.
     */
    private final List<Pet> pets = new ArrayList<>();

    /**
     * Crea un cliente con sus datos de contacto y domicilio.
     */
    public Client(int id, String firstName, String lastName, String phone, String email, String address) {
        super(id, firstName, lastName, phone, email);
        setAddress(address);
    }

    /**
     * Obtiene el domicilio del cliente.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Actualiza el domicilio después de validar que no esté vacío.
     */
    public void setAddress(String address) {
        Validatable.requiredText(address, "dirección");
        this.address = address;
    }

    /**
     * Devuelve las mascotas registradas para este cliente.
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Asocia una mascota al cliente sin duplicar referencias existentes.
     */
    public void registerPet(Pet pet) {
        Objects.requireNonNull(pet, "La mascota no puede ser nula");
        if (!pets.contains(pet)) {
            pets.add(pet);
        }
    }
}
