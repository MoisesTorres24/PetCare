package org.petcare.model.clinic;

import org.petcare.model.person.Employee;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    /** Consultorios disponibles en la clínica. */
    private Map<String, ConsultingRoom> rooms;
    /** Citas registradas en la clínica. */
    private List<Appointment> appointments;

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
        initializeCollections();
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
        initializeCollections();
        initializeRooms(numberOfRooms);
    }

    /**
     * Inicializa las colecciones que almacenan recursos y citas de la clínica.
     */
    private void initializeCollections() {
        this.rooms = new TreeMap<>();
        this.appointments = new ArrayList<>();
    }

    /**
     * Crea los consultorios numerados solicitados por la clínica.
     *
     * @param numberOfRooms cantidad de consultorios a crear
     */
    private void initializeRooms(int numberOfRooms) {
        for (int roomId = 1; roomId <= numberOfRooms; roomId++) {
            addRoom(new ConsultingRoom(String.valueOf(roomId)));
        }
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
        if (veterinarian == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        if (rooms.isEmpty() || veterinarians.size() >= rooms.size()) {
            throw new IllegalArgumentException("No hay consultorios disponibles");
        }
        veterinarian.setConsultingRoom(getAvailableRoom());
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

    /**
     * Reúne las mascotas de todos los clientes registrados.
     *
     * @return colección no modificable de mascotas
     */
    public Collection<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        for (Client client : clients.values()) {
            pets.addAll(client.getPets());
        }
        return Collections.unmodifiableCollection(pets);
    }

    /**
     * Obtiene los consultorios registrados.
     *
     * @return colección no modificable de consultorios
     */
    public Collection<ConsultingRoom> getAllRooms() {
        return Collections.unmodifiableCollection(rooms.values());
    }

    /**
     * Agrega una cita a la agenda de la clínica.
     *
     * @param appointment cita que se incorporará
     * @throws IllegalArgumentException si la cita es nula
     */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        appointments.add(appointment);
    }

    /**
     * Obtiene la agenda sin permitir modificaciones estructurales externas.
     *
     * @return citas registradas
     */
    public Collection<Appointment> getAppointments() {
        return Collections.unmodifiableCollection(appointments);
    }

    /**
     * Alias para obtener todas las citas registradas.
     *
     * @return citas registradas
     */
    public Collection<Appointment> getAllAppointments() {
        return getAppointments();
    }

    /**
     * Localiza el primer consultorio que aún no está asignado a un veterinario.
     *
     * @return consultorio disponible
     * @throws IllegalArgumentException si no hay consultorios libres
     */
    private ConsultingRoom getAvailableRoom() {
        for (ConsultingRoom room : rooms.values()) {
            if (!isRoomAssigned(room)) {
                return room;
            }
        }
        throw new IllegalArgumentException("No hay consultorios disponibles");
    }

    /**
     * Comprueba si un consultorio ya está asignado a un veterinario.
     *
     * @param room consultorio que se desea comprobar
     * @return {@code true} si ya tiene un veterinario asignado
     */
    private boolean isRoomAssigned(ConsultingRoom room) {
        for (Veterinarian veterinarian : veterinarians.values()) {
            if (room.equals(veterinarian.getConsultingRoom())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Punto de extensión para registrar empleados genéricos.
     *
     * @param employee empleado que se desea registrar
     */
    public void addEmployee(Employee employee) {

    }

    /**
     * Agrega un consultorio usando su número como identificador.
     *
     * @param room consultorio que se incorporará
     * @throws IllegalArgumentException si el consultorio es nulo
     */
    public void addRoom(ConsultingRoom room) {
        if (room == null) {
            throw new IllegalArgumentException("El consultorio no puede ser nulo");
        }
        rooms.put(room.getRoomNumber(), room);
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
