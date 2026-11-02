package org.petcare.model.clinic;

import org.petcare.model.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Comprobante de los servicios cobrados en una atención.
 */
public class Receipt {
    /**
     * Contador estático de comprobantes emitidos por la clínica.
     */
    private static int receiptCounter = 0;
    /**
     * Número de folio único de este comprobante.
     */
    private final int receiptNumber;
    /**
     * Importe total del comprobante.
     */
    private final double totalAmount;
    /**
     * Fecha de emisión.
     */
    private final LocalDate issueDate;
    /**
     * Servicios incluidos en el comprobante.
     */
    private final List<Service> services;

    /**
     * Crea un comprobante y copia la lista de servicios recibida.
     */
    public Receipt(double totalAmount, LocalDate issueDate, List<Service> services) {
        receiptCounter++;
        this.receiptNumber = receiptCounter;
        this.totalAmount = totalAmount;
        this.issueDate = issueDate;
        this.services = new ArrayList<>(services);
    }

    /**
     * Imprime en consola el desglose del comprobante de pago.
     */
    public void generateTicket() {
        System.out.println("=========================================");
        System.out.println("       COMPROBANTE DE PAGO #" + receiptNumber);
        System.out.println("Fecha de emisión: " + issueDate);
        System.out.println("-----------------------------------------");
        System.out.println("Servicios cobrados:");
        // Se recorre la lista de servicios polimórficamente
        for (Service service : services) {
            System.out.println(" - " + service.getName() + ": $" + service.calculateCost());
        }
        System.out.println("-----------------------------------------");
        System.out.println("TOTAL A PAGAR: $" + totalAmount);
        System.out.println("=========================================");
    }

    /**
     * Obtiene el número de folio del comprobante.
     */
    public int getReceiptNumber() {
        return receiptNumber;
    }

    /**
     * Obtiene el total de comprobantes generados en el sistema.
     */
    public static int getReceiptCounter() {
        return receiptCounter;
    }

    /**
     * Obtiene el importe total.
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Obtiene la fecha de emisión.
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Obtiene los servicios incluidos.
     */
    public List<Service> getServices() {
        return services;
    }
}