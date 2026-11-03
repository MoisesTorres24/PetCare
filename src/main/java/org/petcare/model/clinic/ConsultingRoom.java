package org.petcare.model.clinic;

/** Representa un consultorio identificado dentro de la clínica. */
public class ConsultingRoom {
    /** Número o código del consultorio. */
    private String roomNumber;

    /** Crea un consultorio con su identificador. */
    public ConsultingRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

//    public boolean isAvailable(LocalDateTime dateTime) {
//
//    }


    /** Obtiene el identificador del consultorio. */
    public String getRoomNumber() {
        return roomNumber;
    }

    /** Actualiza el identificador del consultorio. */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
