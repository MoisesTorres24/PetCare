package org.petcare.model.clinic;

import org.petcare.model.person.Employee;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/** Datos básicos de una clínica veterinaria. */
public class Clinic {
    /** Nombre comercial de la clínica. */
    private String name;
    /** Dirección física de la clínica. */
    private String address;
    /** Consultorios disponibles en la clínica. */
    private int numberOfRooms;
    /** Clientes registrados, indexados por su identificador. */
    private Map<Integer, Client> clients;
    /** Veterinarios registrados, indexados por su identificador laboral. */
    private Map<Integer, Veterinarian> veterinarians;

    /**
     * Crea una clínica con nombre y dirección.
     *
     * @param name nombre comercial de la clínica
     * @param address dirección física de la clínica
     */
    public Clinic(String name, String address) {
        this.name = name;
        this.address = address;
        this.clients = new TreeMap<>();
        this.veterinarians = new TreeMap<>();
    }

    /**
     * Crea una clínica con nombre y número de consultorios.
     *
     * @param name nombre comercial de la clínica
     * @param numberOfRooms cantidad de consultorios disponibles
     */
    public Clinic(String name, int numberOfRooms) {
        this.name = name;
        this.address = "";
        this.numberOfRooms = numberOfRooms;
        this.clients = new TreeMap<>();
        this.veterinarians = new TreeMap<>();
    }

    /**
     * Guarda un cliente usando su identificador como llave.
     *
     * @param client cliente que se incorporará a la clínica
     */
    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param id identificador del cliente
     * @return cliente encontrado o {@code null} si no existe
     */
    public Client getClientById(int id) {
        return clients.get(id);
    }

    /**
     * Obtiene los clientes registrados sin permitir modificaciones externas.
     *
     * @return colección de clientes registrados
     */
    public Collection<Client> getAllClients() {
        return Collections.unmodifiableCollection(clients.values());
    }

    /**
     * Comprueba si la clínica tiene clientes registrados.
     *
     * @return {@code true} cuando existe al menos un cliente
     */
    public boolean hasClients() {
        return !clients.isEmpty();
    }

    /**
     * Registra un veterinario en la clínica.
     *
     * @param veterinarian veterinario que se incorporará a la clínica
     */
    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarians.put(veterinarian.getEmployeeId(), veterinarian);
    }

    /**
     * Obtiene los veterinarios registrados sin permitir modificaciones externas.
     *
     * @return colección de veterinarios registrados
     */
    public Collection<Veterinarian> getAllVeterinarians() {
        return Collections.unmodifiableCollection(veterinarians.values());
    }

    /** Registra un empleado en la clínica cuando se implemente su almacenamiento. */
    public void addEmployee(Employee employee) {

    }

    /** Registra un consultorio cuando se implemente su almacenamiento. */
    public void addRoom(ConsultingRoom room) {

    }

    /** Obtiene el nombre de la clínica. */
    public String getName() {
        return name;
    }

    /** Actualiza el nombre de la clínica. */
    public void setName(String name) {
        this.name = name;
    }

    /** Obtiene la dirección de la clínica. */
    public String getAddress() {
        return address;
    }

    /** Actualiza la dirección de la clínica. */
    public void setAddress(String address) {
        this.address = address;
    }
}
