package org.petcare.controller;

import org.petcare.model.person.Client;
import org.petcare.model.pet.Cat;
import org.petcare.model.pet.Dog;
import org.petcare.model.pet.ExoticBird;

import java.util.*;

/**
 * Controlador principal de la clínica veterinaria.
 * Gestiona colecciones y flujos de negocio sin interactuar con la consola.
 */
public class RegistrationController {
    /**
     * Clientes registrados, indexados por su identificador.
     */
    private TreeMap<Integer, Client> clients;
    /**
     * Siguiente identificador disponible para un cliente.
     */
    private int nextClientId;
    /**
     * Siguiente identificador disponible para una mascota.
     */
    private int nextPetId;

    /**
     * Inicializa las colecciones y los contadores de identificadores.
     */
    public RegistrationController() {
        this.clients = new TreeMap<>();
        this.nextClientId = 1;
        this.nextPetId = 1;
    }

    /**
     * Indica si existe al menos un cliente registrado.
     */
    public boolean hasClients() {
        return !clients.isEmpty();
    }

    /**
     * Busca un cliente por su identificador.
     */
    public Client findClientById(int id) {
        return clients.get(id);
    }

    /**
     * Devuelve una vista de solo lectura de los clientes registrados.
     */
    public Collection<Client> getAllClients() {
        return Collections.unmodifiableCollection(clients.values());
    }


    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @return El cliente recién creado.
     */
    public Client registerClient(String firstName, String lastName, String phone, String email, String address) {
        Client client = new Client(nextClientId, firstName, lastName, phone, email, address);
        clients.put(client.getId(), client);
        nextClientId++;
        return client;
    }

    /**
     * Registra un perro y lo asocia a un cliente existente.
     */
    public Dog registerDog(int clientId, String petName, int age, double weight, String breed, String energyLevel) {
        Client client = getRequiredClient(clientId);
        Dog dog = new Dog(nextPetId, petName, age, weight, client, breed, energyLevel);
        client.registerPet(dog);
        nextPetId++;
        return dog;
    }

    /**
     * Registra un gato y lo asocia a un cliente existente.
     */
    public Cat registerCat(int clientId, String petName, int age, double weight, boolean domestic) {
        Client client = getRequiredClient(clientId);
        Cat cat = new Cat(nextPetId, petName, age, weight, client, domestic);
        client.registerPet(cat);
        nextPetId++;
        return cat;
    }

    /**
     * Registra un ave exótica y la asocia a un cliente existente.
     */
    public ExoticBird registerExoticBird(int clientId, String petName, int age, double weight, double wingSpan) {
        Client client = getRequiredClient(clientId);
        ExoticBird bird = new ExoticBird(nextPetId, petName, age, weight, client, wingSpan);
        client.registerPet(bird);
        nextPetId++;
        return bird;
    }

    private Client getRequiredClient(int clientId) {
        Client client = clients.get(clientId);
        if (client == null) {
            throw new IllegalArgumentException("No existe un dueño con el ID " + clientId);
        }
        return client;
    }
}