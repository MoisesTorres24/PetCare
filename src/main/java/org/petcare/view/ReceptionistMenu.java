package org.petcare.view;

import org.petcare.controller.ClinicController;

import java.util.Scanner;

public class ReceptionistMenu implements Menu {
    private ClinicController controller;
    private Scanner scanner;


    @Override
    public void displayOptions() {

    }

    @Override
    public int readUserInput() {
        return 0;
    }
}
