package org.petcare.model.person;

import org.petcare.util.Validatable;

/**
 * Información de contacto compartida por clientes y empleados.
 */
public abstract class Person {
    /**
     * Identificador interno de la persona.
     */
    private int id;
    /**
     * Nombre de pila.
     */
    private String firstName;
    /**
     * Apellido.
     */
    private String lastName;
    /**
     * Teléfono de contacto.
     */
    private String phone;
    /**
     * Correo electrónico de contacto.
     */
    private String email;

    /**
     * Inicializa la identidad y los datos de contacto de la persona.
     */
    protected Person(int id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setEmail(email);
    }

    /**
     * Obtiene el identificador interno.
     */
    public int getId() {
        return id;
    }

    /**
     * Actualiza el identificador interno.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Actualiza el nombre validando que sea obligatorio.
     */
    public void setFirstName(String firstName) {
        Validatable.requiredText(firstName, "nombre");
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Actualiza el apellido validando que sea obligatorio.
     */
    public void setLastName(String lastName) {
        Validatable.requiredText(lastName, "apellido");
        this.lastName = lastName;
    }

    /**
     * Obtiene el teléfono.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Actualiza el teléfono validando que sea obligatorio.
     */
    public void setPhone(String phone) {
        Validatable.requiredText(phone, "teléfono");
        this.phone = phone;
    }

    /**
     * Obtiene el correo electrónico.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el correo validando que sea obligatorio.
     */
    public void setEmail(String email) {
        Validatable.requiredText(email, "correo");
        this.email = email;
    }
}
