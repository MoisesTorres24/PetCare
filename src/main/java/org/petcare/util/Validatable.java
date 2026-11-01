package org.petcare.util;

/**
 * Utilidades estáticas para validar datos del dominio.
 */
public final class Validatable {
    /**
     * Impide crear instancias de esta clase utilitaria.
     */
    private Validatable() {
    }

    /**
     * Verifica que un entero no sea negativo.
     */
    public static void nonNegativeInteger(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException("el " + fieldName + " no puede ser negativo");
        }
    }

    /**
     * Verifica que un decimal sea finito y no negativo.
     */
    public static void nonNegativeDecimal(double value, String fieldName) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0) {
            throw new IllegalArgumentException("el " + fieldName + " no puede ser negativo");
        }
    }

    /**
     * Verifica que un texto exista y no contenga solo espacios.
     */
    public static void requiredText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("el " + fieldName + " es obligatorio");
        }
    }
}
