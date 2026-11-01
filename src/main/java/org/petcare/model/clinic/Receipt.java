package org.petcare.model.clinic;

import org.petcare.model.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final double totalAmount;
    private final LocalDate issueDate;
    private final List<Service> services;

    public Receipt(double totalAmount, LocalDate issueDate, List<Service> services) {
        this.totalAmount = totalAmount;
        this.issueDate = issueDate;
        this.services = new ArrayList<>(services);
    }

    public void generateTicket() {
        // logica del ticket
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public List<Service> getServices() {
        return services;
    }
}
