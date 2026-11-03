import org.petcare.controller.AppointmentController;
import org.petcare.controller.ClinicOperationsController;
import org.petcare.controller.RegistrationController;
import org.petcare.model.clinic.Appointment;
import org.petcare.model.clinic.AppointmentStatus;
import org.petcare.model.clinic.Clinic;
import org.petcare.model.person.Client;
import org.petcare.model.person.Veterinarian;
import org.petcare.model.pet.Pet;
import org.petcare.model.service.GeneralConsultation;
import org.petcare.model.service.Surgery;
import org.petcare.model.service.SurgeryComplexity;
import org.petcare.model.service.Vaccination;
import org.petcare.model.service.VaccineType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class Test {
    static void main(String[] args) {
        // Instanciamos la clínica y sus tres controladores
        Clinic clinic = new Clinic("PetCare", 5);
        RegistrationController reg = new RegistrationController(clinic);
        AppointmentController appCtrl = new AppointmentController(clinic);
        ClinicOperationsController ops = new ClinicOperationsController(clinic);

        // 1. Registrar dos dueños y tres mascotas (un perro, un gato y un ave)
        Client c1 = reg.registerClient("Carlos", "Rivera", "9211112233", "carlos@gmail.com", "Centro");
        Client c2 = reg.registerClient("Elena", "Gómez", "9214445566", "elena@gmail.com", "Norte");
        Pet dog = reg.registerDog(c1.getId(), "Max", 4, 18.5, "Pastor Alemán", "Alta");
        Pet cat = reg.registerCat(c1.getId(), "Luna", 2, 4.2, true);
        Pet bird = reg.registerExoticBird(c2.getId(), "Kiwi", 1, 0.4, 28.0);

        // 2. Registrar dos veterinarios
        Veterinarian v1 = reg.registerVeterinarian("Roberto", "Sánchez", "9210001", "roberto@petcare.com", "CED-01", "General");
        Veterinarian v2 = reg.registerVeterinarian("Laura", "Martínez", "9210002", "laura@petcare.com", "CED-02", "Cirugía");

        // 3. Agendar tres citas y atender dos (calculando consulta, vacunación y cirugía)
        Appointment a1 = appCtrl.scheduleAppointment(dog, v1, LocalDateTime.of(2026, 11, 10, 10, 0),
                Arrays.asList(new GeneralConsultation(), new Vaccination(VaccineType.RABIES)));
        Appointment a2 = appCtrl.scheduleAppointment(cat, v2, LocalDateTime.of(2026, 11, 10, 12, 0),
                List.of(new Surgery(2.0, SurgeryComplexity.MEDIUM)));
        Appointment a3 = appCtrl.scheduleAppointment(bird, v1, LocalDateTime.of(2026, 11, 11, 9, 0),
                List.of(new GeneralConsultation()));

        // 4. Atendemos las dos primeras citas e imprimimos sus comprobantes con el costo total
        appCtrl.changeAppointmentStatus(a1.getId(), AppointmentStatus.COMPLETED);
        appCtrl.changeAppointmentStatus(a2.getId(), AppointmentStatus.COMPLETED);
        ops.generateReceiptForAppointment(a1.getId()).generateTicket();
        ops.generateReceiptForAppointment(a2.getId()).generateTicket();

        // 5. Intentar agendar una cita que viole una regla de negocio y mostrar el error
        try {
            // Misma fecha, hora y veterinario que la cita a3 (que sigue programada)
            appCtrl.scheduleAppointment(dog, v1, a3.getDateTime(), List.of(new GeneralConsultation()));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 6. Listar las mascotas llamando a makeSound() para mostrar el polimorfismo
        System.out.println("\n=== Mascotas y sonidos ===");
        for (Pet pet : ops.getAllPets()) {
            System.out.print(pet.getName() + ": ");
            pet.makeSound();
        }

        // 7. Mostrar el total cobrado y consultar un historial clínico
        System.out.println("\n=== Total cobrado por veterinario ===");
        System.out.println("Dr. " + v1.getFirstName() + ": $" + ops.calculateTotalCollectedByVeterinarian(v1.getEmployeeId()));
        System.out.println("Dra. " + v2.getFirstName() + ": $" + ops.calculateTotalCollectedByVeterinarian(v2.getEmployeeId()));

        System.out.println("\n=== Historial clínico de " + dog.getName() + " ===");
        for (Appointment record : ops.getPetMedicalHistory(dog.getId())) {
            System.out.println(record);
        }
        System.out.println("Total cobrado a " + dog.getName() + ": $" + ops.calculateTotalCollectedByPet(dog.getId()));
    }
}