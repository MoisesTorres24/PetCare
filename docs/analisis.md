### **1\. Tabla de Identificación del Sistema**

| Nombre | Tipo | Atributos / Constantes | Responsabilidades (Métodos) | Justificación |
| :---- | :---- | :---- | :---- | :---- |
| **Validatable** | Clase de utilidades | (No aplica) | nonNegativeInteger(), nonNegativeDecimal(), requiredText(): Validaciones estáticas de integridad. | Centraliza las reglas de validación sin obligar a las entidades a implementar una interfaz. |
| **InputHelper** | Clase de utilidades | (No aplica) | readText(), readInt(), readDecimal(), readOption(): Captura segura por consola con reintento automático. | Aísla la lectura y validación de entradas del usuario por consola para evitar duplicar controles en las vistas. |
| **Person** | Abstracta | id: Identificador único. firstName: Nombre de pila. lastName: Apellidos. phone: Teléfono de contacto. email: Correo electrónico. | setFirstName(), setLastName(), setPhone(), setEmail(): Asignan y validan los datos básicos de contacto obligatorios. | Centraliza la información personal compartida por clientes y empleados, evitando código duplicado. |
| **Client** | Concreta | address: Domicilio del cliente. pets (List\<Pet\>): Mascotas asociadas. | registerPet(): Asocia una mascota a este cliente evitando referencias nulas o duplicadas. | Representa al dueño de las mascotas y sirve como identificador clave en las búsquedas y registros del sistema. |
| **Employee** | Abstracta | employeeId: Número de empleado. role (EmployeeRole): Cargo administrativo. baseSalary: Sueldo base. | setBaseSalary(): Lanza excepción si el salario asignado es negativo. | Define la estructura administrativa y salarial común del personal de la clínica. |
| **Veterinarian** | Concreta | medicalLicense: Cédula profesional. specialty: Área médica de especialidad. consultingRoom (ConsultingRoom): Consultorio fijo asignado. | setConsultingRoom(): Asigna el consultorio fijo validando que no sea nulo. | Proporciona los servicios médicos en un consultorio asignado y permite calcular los ingresos generados por sus consultas. |
| **Receptionist** | Concreta | assignedShift (ShiftType): Turno laboral asignado. | setAssignedShift(): Consulta y modifica el turno del empleado rechazando valores nulos. | Define el perfil del personal operativo de recepción con un horario laboral específico. |
| **Pet** | Abstracta | id: Identificador de la mascota. name: Nombre. age: Edad en años. weight: Peso en kilogramos. owner (Client): Referencia a su dueño. history (MedicalHistory): Registro de citas. | makeSound() *(abstracto)*: Comportamiento sonoro de la especie. setAge(), setWeight(): Lanzan excepción si la edad o el peso son menores o iguales a cero. | Centraliza las características biológicas básicas y vincula al animal con su dueño y su historial médico mediante composición. |
| **Dog** | Concreta | breed: Raza del perro. energyLevel: Nivel de energía. | makeSound(): Imprime el ladrido en consola. | Modela un perro permitiendo registrar características específicas para caninos como su raza y energía. |
| **Cat** | Concreta | isDomestic: Indica si es gato de casa o feral. | makeSound(): Imprime el maullido en consola. | Modela un gato permitiendo registrar características particulares de los felinos. |
| **ExoticBird** | Concreta | wingSpan: Envergadura de las alas en centímetros. | makeSound(): Imprime el canto del ave en consola. setWingSpan(): Valida que la envergadura sea mayor que cero. | Modela un ave exótica habilitando el registro de datos morfológicos específicos. |
| **Clinic** | Concreta | name: Nombre comercial. address: Ubicación física. numberOfRooms: Cantidad de consultorios. clients (Map\<Integer, Client\>). veterinarians (Map\<Integer, Veterinarian\>). rooms (Map\<String, ConsultingRoom\>). appointments (List\<Appointment\>). | addClient(), getClientById(): Gestión de dueños indexados. addVeterinarian(): Incorpora un veterinario asignándole un consultorio disponible. addRoom(), addAppointment(): Registra consultorios y citas. getAllPets(): Reúne las mascotas de todos los clientes. | Gestiona la infraestructura física, el catálogo de clientes, el personal médico y la agenda general de la institución. |
| **ConsultingRoom** | Concreta | roomNumber: Código del espacio físico. | getRoomNumber(), setRoomNumber(): Obtienen y actualizan el identificador del consultorio. | Representa el espacio físico asignado a los veterinarios donde ocurren las citas médicas. |
| **Appointment** | Concreta (Comparable) | id: Identificador de la cita. pet (Pet): Mascota. veterinarian (Veterinarian): Médico. services (List\<Service\>): Servicios a realizar. room (ConsultingRoom): Consultorio. dateTime (LocalDateTime): Fecha y hora. status (AppointmentStatus): Estado actual. | setStatus(): Valida las reglas de transición de estado (atendida o cancelada) y guarda la cita en el historial al completarse. compareTo(): Compara citas por fecha, hora e identificador. | Gestiona la transacción principal del negocio, vinculando recursos físicos con mascotas, veterinarios y servicios. |
| **MedicalHistory** | Concreta | appointments (TreeSet\<Appointment\>): Colección ordenada de citas. | addRecord(): Inserta una cita manteniendo el orden cronológico. getRecords(): Devuelve el conjunto inmodificable de citas del historial. | Mantiene el registro médico ordenado por fechas de manera automática y sin duplicaciones. |
| **Billable** | Interfaz | (No aplica) | calculateCost(): Calcula el monto total a cobrar. | Define el contrato para todos los elementos y servicios facturables, permitiendo sumarlos polimórficamente. |
| **Service** | Abstracta (Billable) | name: Nombre del procedimiento. | calculateCost() *(abstracto)*: Fórmula para determinar el precio según el servicio. | Define la base común para aplicar distintos métodos de cobro según el procedimiento médico. |
| **GeneralConsultation** | Concreta | FIXED\_FEE (500.0). baseFee: Tarifa estándar por revisión. | calculateCost(): Retorna la tarifa base sin modificaciones. | Modela el cobro de una revisión médica general de rutina. |
| **Vaccination** | Concreta | DEFAULT\_APPLICATION\_FEE (100.0). baseFee: Costo de aplicación. vaccineType (VaccineType): Biológico. | calculateCost(): Suma la tarifa de aplicación al costo de la vacuna. | Calcula el precio total dependiendo del biológico específico suministrado. |
| **Surgery** | Concreta | HOURLY\_RATE (800.0). hours: Duración en quirófano. complexityLevel (SurgeryComplexity): Nivel de riesgo. | calculateCost(): Multiplica las horas por la tarifa fija y el factor de riesgo. setHours(): Valida que las horas sean mayores que cero. | Calcula el precio de procedimientos quirúrgicos basándose en el tiempo de quirófano y la complejidad médica. |
| **Receipt** | Concreta | receiptCounter: Contador estático de folios. receiptNumber: Folio único. totalAmount: Monto final. issueDate (LocalDate): Fecha de expedición. services (List\<Service\>): Servicios cobrados. | generateTicket(): Imprime el desglose polimórfico de servicios y el total a pagar. | Consolida el cobro final de una cita atendida en un registro contable inmutable. |
| **RegistrationController** | Concreta | clinic (Clinic): Clínica activa. nextClientId, nextPetId, nextVeterinarianId: Contadores de identificadores. | registerClient(), registerVeterinarian(): Crean y registran clientes y médicos. registerDog(), registerCat(), registerExoticBird(): Crean mascotas y las asocian a su dueño. findClientById(): Consulta dueños por ID. | Coordina las altas de clientes, mascotas y veterinarios manteniendo la generación secuencial de identificadores. |
| **AppointmentController** | Concreta | clinic (Clinic): Clínica activa. nextAppointmentId: Contador de citas. | scheduleAppointment(): Agenda citas validando disponibilidad del médico y consultorio. changeAppointmentStatus(): Cambia el estado a atendida o cancelada. findAppointment(): Búsqueda sobrecargada por ID o por fecha (LocalDate). | Gestiona las reglas de agendamiento, evita empalmes de horario y controla el ciclo de vida de las citas. |
| **ClinicOperationsController** | Concreta | clinic (Clinic): Clínica activa. | generateReceiptForAppointment(): Genera el comprobante de una cita atendida. getPetMedicalHistory(): Obtiene el historial ordenado de una mascota. calculateTotalCollectedByVeterinarian(), calculateTotalCollectedByPet(): Calculan ingresos acumulados. removePet(): Cancela citas futuras y da de baja a la mascota junto con su historial. | Ejecuta las operaciones de facturación, consulta de historiales, reportes de cobros y bajas del sistema. |
| **Menu** | Interfaz | (No aplica) | displayOptions(): Muestra opciones en consola. readUserInput(): Lee la selección del usuario. | Estandariza la forma en que el menú interactúa con la consola de texto. |
| **AdminMenu** | Concreta (Menu) | EXIT\_OPTION (11). registrationHelper (RegistrationView). appointmentView (AppointmentView). operationsView (ClinicOperationsView). scanner (Scanner). | displayOptions(), readUserInput(): Muestran el menú principal y delegan la opción elegida a la vista correspondiente. | Centraliza el flujo de navegación principal y coordina las vistas especializadas del sistema. |
| **RegistrationView** | Concreta | controller (RegistrationController). scanner (Scanner). | registerClientWithPet(), registerPetToExistingClient(), registerVeterinarian(): Capturan en consola los datos para el alta de dueños, mascotas y médicos. | Separa la interacción por consola para los flujos de registro de entidades. |
| **AppointmentView** | Concreta | appointmentController (AppointmentController). clinic (Clinic). scanner (Scanner). | displayScheduleAppointment(), displayManageAppointment(): Solicitan datos en consola para agendar, atender o cancelar citas. | Gestiona la captura de fechas, selección de servicios y administración de citas en consola. |
| **ClinicOperationsView** | Concreta | operationsController (ClinicOperationsController). clinic (Clinic). scanner (Scanner). | displayGenerateReceipt(), displayMedicalHistory(), displayPetsAndSounds(), displayTotalByVeterinarian(), displayRemovePet(): Muestran comprobantes, historiales, sonidos, cobros y bajas. | Presenta en consola las consultas operativas, reportes financieros y listados polimórficos. |
| **Main** | Concreta | (No aplica) | main(): Inicializa la clínica, los controladores, las vistas y ejecuta el menú principal. | Punto de entrada que ensambla las dependencias e inicia la aplicación. |
| **AppointmentStatus** | Enum | SCHEDULED, CANCELED, COMPLETED | (Sin métodos adicionales) | Restringe los estados posibles de una cita médica, evitando errores en el flujo lógico. |
| **VaccineType** | Enum | RABIES (250.0), PARVOVIRUS (300.0). Atributo interno: cost: Precio del biológico. | getCost(): Retorna el costo unitario de la vacuna. | Encapsula los biológicos y su precio fijo, ahorrando memoria al no instanciar múltiples objetos por vacuna. |
| **SurgeryComplexity** | Enum | LOW (1.0), MEDIUM (1.5), HIGH (2.5). Atributo interno: riskMultiplier: Factor matemático. | getRiskMultiplier(): Retorna el multiplicador de riesgo. | Clasifica el nivel de riesgo quirúrgico, facilitando el cálculo polimórfico del costo en el servicio. |
| **EmployeeRole** | Enum | ADMINISTRATOR, RECEPTIONIST, VETERINARIAN | (Sin métodos adicionales) | Define los cargos disponibles para clasificar al personal dentro de la clínica. |
| **ShiftType** | Enum | MORNING, EVENING, NIGHT | (Sin métodos adicionales) | Clasifica el horario de trabajo del personal sin la complejidad de registrar horas exactas por empleado. |

### **2\. Clases Descartadas**

**Vaccine (Vacuna):** Se descartó como clase entidad porque no se requiere gestión de inventarios, proveedores o caducidades; un Enum (VaccineType) con un atributo de costo resuelve el requerimiento.  
**Shift (Turno):** Se descartó porque el sistema no registra el control de asistencia con horas dinámicas de entrada/salida; un Enum (ShiftType) clasifica los horarios.

### **3\. Objetos de Ejemplo**

**1\. Objeto de tipo Client (Dueño)**

* id: 1
* firstName: "Moisés Gerónimo"
* lastName: "Torres González"
* phone: "9215690297"
* email: "mt242193@gmail.com"
* address: "Minatitlán, Veracruz"
* pets: \[Lista con referencia a Dog\]

**2\. Objeto de tipo Dog (Mascota)**

* id: 101
* name: "Hercules"
* age: 3
* weight: 12.5
* owner: \[Referencia a Client\]
* history: \[Objeto MedicalHistory\]
* breed: "Mestizo"
* energyLevel: "Alto"

**3\. Objeto de tipo Appointment (Cita)**

* id: 1
* pet: \[Referencia a Dog\]
* veterinarian: \[Referencia a Veterinarian\]
* room: \[Referencia a ConsultingRoom\]
* dateTime: 2026-11-05T10:00:00
* services: \[Lista con un objeto GeneralConsultation\]
* status: AppointmentStatus.SCHEDULED

### **4\. Notas generales**

Me basé en cumplir lo solicitado en el documento y en las reglas del negocio, agregando mejoras en la estructura del modelo y en la organización por capas. Estructuré el personal mediante la clase abstracta Employee (heredera de Person), lo que permitió incorporar la gestión de salarios base, cargos (EmployeeRole) y turnos (ShiftType) para modelar la jerarquía laboral de la clínica. Además, para evitar concentrar toda la lógica y la captura de datos en una sola clase, dividí la arquitectura de controladores y vistas por responsabilidades (RegistrationController, AppointmentController y ClinicOperationsController, junto con sus respectivas vistas auxiliares coordinadas desde AdminMenu) e integré la interfaz Billable para realizar el cálculo de costos de los servicios de forma polimórfica.

### **5\. Uso de IA**

Utilicé la IA como un tutor que me ayudó a diseñar las clases y a descartar varias ideas erróneas que tenía. Me enfoqué en dar propuestas basadas en la descripción del problema y las reglas del negocio y la IA evaluaba esas propuestas; reforcé mis conocimientos sobre los conceptos de composición y agregación en Java. También usé la IA para dar formato a este documento y nombrar mis clases y métodos en el idioma inglés.