package org.petcare.controller;

import org.petcare.model.clinic.Appointment;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;

import java.util.*;

public class ClinicController {
    private Map<Client, List<Pet>> clientPets = new HashMap<>();
    private Queue<Pet> waitingRoom = new LinkedList<>();

    public ClinicController(Map<Client, List<Pet>> clientPets, Queue<Pet> waitingRoom) {
        this.clientPets = clientPets;
        this.waitingRoom = waitingRoom;
    }

//    public Appointment scheduleAppointment(){
//
//    }


//    public double calculateVetIncome(Veterinarian vet){
//
//    }


    public Map<Client, List<Pet>> getClientPets() {
        return clientPets;
    }

    public void setClientPets(Map<Client, List<Pet>> clientPets) {
        this.clientPets = clientPets;
    }

    public Queue<Pet> getWaitingRoom() {
        return waitingRoom;
    }

    public void setWaitingRoom(Queue<Pet> waitingRoom) {
        this.waitingRoom = waitingRoom;
    }
}
