package org.petcare.model.clinic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ConsultingRoom {
    private String roomNumber;

    public ConsultingRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

//    public boolean isAvailable(LocalDateTime dateTime) {
//
//    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
