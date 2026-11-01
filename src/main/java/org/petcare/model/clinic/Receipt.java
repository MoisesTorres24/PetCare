package org.petcare.model.clinic;

import org.petcare.model.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Comprobante de los servicios cobrados en una atención. */
public class Receipt {
    /** Importe total del comprobante. */
    private final double totalAmount;
    /** Fecha de emisión. */
    private final LocalDate issueDate;
    /** Servicios incluidos en el comprobante. */
    private final List<Service> services;

    /** Crea un comprobante y copia la lista de servicios recibida. */
    public Receipt(double totalAmount, LocalDate issueDate, List<Service> services) {
        this.totalAmount = totalAmount;
        this.issueDate = issueDate;
        this.services = new ArrayList<>(services);
    }

    /** Genera la representación del comprobante; actualmente solo deja el punto de extensión. */
    public void generateTicket() {
        // logica del ticket
    }

    /** Obtiene el importe total. */
    public double getTotalAmount() {
        return totalAmount;
    }

    /** Obtiene la fecha de emisión. */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /** Obtiene los servicios incluidos. */
    public List<Service> getServices() {
        return services;
    }
}
