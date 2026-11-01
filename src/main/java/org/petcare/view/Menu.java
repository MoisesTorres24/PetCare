package org.petcare.view;

/**
 * Contrato común para los menús interactivos de la aplicación.
 */
public interface Menu {
    /**
     * Presenta las opciones y procesa la interacción del usuario.
     */
    void displayOptions();

    /**
     * Lee la opción elegida por el usuario.
     */
    int readUserInput();
}

