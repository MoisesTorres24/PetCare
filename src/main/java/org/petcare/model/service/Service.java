package org.petcare.model.service;

public abstract class Service {
    private String name;

    protected Service(String name) {
        this.name = name;
    }

    public abstract double calculateCost();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}