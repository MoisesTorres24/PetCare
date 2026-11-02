package org.petcare.controller;

import org.petcare.model.clinic.Clinic;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Cat;
import org.petcare.model.pet.Dog;
import org.petcare.model.pet.ExoticBird;

import java.util.Collection;

/**
 * Controlador principal de la clínica veterinaria.
 * Gestiona colecciones y flujos de negocio sin interactuar con la consola.
 */
public class RegistrationController {
    /** Clínica que centraliza los clientes registrados. */
    private Clinic clinic;
    /**
     * Siguiente identificador disponible para un cliente.
     */
    private int nextClientId;
    /**
     * Siguiente identificador disponible para una mascota.
     */
    private int nextPetId;
    /** Siguiente identificador disponible para un veterinario. */
    private int nextVeterinarianId;

    /**
     * Inicializa el controlador con la clínica que almacenará los datos.
     *
     * @param clinic clínica donde se registrarán los clientes y sus mascotas
     */
    public RegistrationController(Clinic clinic) {
        this.clinic = clinic;
        this.nextClientId = 1;
        this.nextPetId = 1;
        this.nextVeterinarianId = 1;
    }

    /**
     * Indica si existe al menos un cliente registrado en la clínica.
     *
     * @return {@code true} cuando la clínica tiene clientes
     */
    public boolean hasClients() {
        return clinic.hasClients();
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param id identificador del cliente
     * @return cliente encontrado o {@code null} si no existe
     */
    public Client findClientById(int id) {
        return clinic.getClientById(id);
    }

    /**
     * Devuelve una vista de solo lectura de los clientes registrados.
     *
     * @return colección de clientes de la clínica
     */
    public Collection<Client> getAllClients() {
        return clinic.getAllClients();
    }


    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param firstName nombre del cliente
     * @param lastName apellido del cliente
     * @param phone teléfono del cliente
     * @param email correo electrónico del cliente
     * @param address domicilio del cliente
     * @return El cliente recién creado.
     */
    public Client registerClient(String firstName, String lastName, String phone, String email, String address) {
        Client client = new Client(nextClientId, firstName, lastName, phone, email, address);
        clinic.addClient(client);
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

    /**
     * Registra un veterinario y lo incorpora a la clínica.
     *
     * @param firstName nombre del veterinario
     * @param lastName apellido del veterinario
     * @param phone teléfono del veterinario
     * @param email correo electrónico del veterinario
     * @param license cédula profesional
     * @param specialty especialidad médica
     * @return veterinario recién creado
     */
    public Veterinarian registerVeterinarian(String firstName, String lastName, String phone,
                                             String email, String license, String specialty) {
        Veterinarian veterinarian = new Veterinarian(
                nextVeterinarianId,
                firstName,
                lastName,
                phone,
                email,
                nextVeterinarianId,
                license,
                specialty
        );
        clinic.addVeterinarian(veterinarian);
        nextVeterinarianId++;
        return veterinarian;
    }

    /**
     * Obtiene un cliente existente o informa que el identificador no es válido.
     *
     * @param clientId identificador del cliente requerido
     * @return cliente encontrado
     * @throws IllegalArgumentException si no existe el cliente
     */
    private Client getRequiredClient(int clientId) {
        Client client = clinic.getClientById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("No existe un dueño con el ID " + clientId);
        }
        return client;
    }
}