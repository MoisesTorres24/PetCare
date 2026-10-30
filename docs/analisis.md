

### **1\. Tabla de Identificación del Sistema**

| Nombre  | Tipo | Atributos / Constantes | Responsabilidades (Métodos) | Justificación en el Dominio |
| :---- | :---- | :---- | :---- | :---- |
| **Validatable** | Interfaz | (No aplica) | validateData(): Valida que los datos numéricos ingresados sean mayores o iguales a cero. | Obliga a las entidades con datos sensibles (edades, costos, salarios) a implementar su propia lógica de validación. |
| **Person** | Abstracta | id: Identificador único. firstName: Nombre de pila. lastName: Apellidos. phone: Teléfono de contacto. email: Correo electrónico. | Getters / Setters: Recuperan y asignan los datos básicos de contacto. | Centraliza la información personal compartida por clientes y empleados, evitando código duplicado. |
| **Client** | Concreta | address: Domicilio del cliente. | registerPet(): Asocia una mascota a este cliente. | Representa al dueño de las mascotas y sirve como identificador clave (llave) en las búsquedas del sistema. |
| **Employee** | Abstracta | employeeId: Número de empleado. role (EmployeeRole): Cargo administrativo. baseSalary: Sueldo base. | validateData(): Lanza excepción si el salario asignado es negativo. | Define la estructura administrativa y salarial del personal de la clínica. |
| **Veterinarian** | Concreta | medicalLicense: Cédula profesional. specialty: Área médica de especialidad. | Getters / Setters: Devuelven la cédula y especialidad médica. | Proporciona los servicios médicos en las citas y permite calcular los ingresos generados por sus consultas. |
| **Receptionist** | Concreta | assignedShift (ShiftType): Turno laboral asignado. | Getters / Setters: Consultan y modifican el turno del empleado. | Define el perfil del usuario operativo que gestiona la agenda y el registro de clientes en ventanilla. |
| **Pet** | Abstracta | id: Identificador de la mascota. name: Nombre. age: Edad en años/meses. weight: Peso en kilogramos. owner (Client): Referencia a su dueño. history (MedicalHistory): Registro de citas. | makeSound() (abstracto): Comportamiento sonoro de la especie. validateData(): Lanza excepción si edad o peso son negativos. | Centraliza las características biológicas básicas y vincula al animal con su historial médico y su dueño. |
| **Dog** | Concreta | breed: Raza del perro. | makeSound(): Imprime el ladrido en consola. | Modela un perro permitiendo filtrar atenciones o características específicas para caninos. |
| **Cat** | Concreta | isDomestic: Indica si es gato de casa o feral. | makeSound(): Imprime el maullido en consola. | Modela un gato, permitiendo registrar características particulares de los felinos. |
| **ExoticBird** | Concreta | wingSpan: Envergadura de las alas en centímetros. | makeSound(): Imprime el canto del ave en consola. | Modela un ave exótica, habilitando el registro de datos morfológicos específicos. |
| **Clinic** | Concreta | name: Nombre comercial. address: Ubicación física. rooms (List): Consultorios. employees (List): Personal. | addEmployee(): Agrega un trabajador a la nómina. addRoom(): Registra un consultorio habilitado. | Gestiona la infraestructura física y los recursos humanos de la institución médica. |
| **ConsultingRoom** | Concreta | roomNumber: Código del espacio físico. | isAvailable(): Verifica si el consultorio está libre en fecha y hora. | Administra la disponibilidad de los espacios donde ocurren las citas médicas. |
| **Appointment** | Concreta | dateTime (LocalDateTime): Fecha/hora. patient: Mascota. vet: Médico. room: Consultorio. services: Servicios a realizar. status: Estado actual. | attend(): Pasa el estado a atendida y emite el comprobante. cancel(): Anula la cita si aún no ocurre. | Gestiona la transacción principal del negocio, vinculando recursos físicos con clientes y servicios. |
| **MedicalHistory** | Concreta | appointments (TreeSet\<Appointment\>): Colección ordenada de citas. | addRecord(): Inserta cita manteniendo orden cronológico. getLastVisit(): Devuelve la cita más reciente. | Mantiene el registro médico ordenado por fechas de manera automática y sin duplicaciones. |
| **Service** | Abstracta | name: Nombre del procedimiento. | calculateCost() (abstracto): Fórmula para determinar precio. | Define el contrato base para aplicar distintos métodos de cobro según el procedimiento médico. |
| **GeneralConsultation** | Concreta | baseFee: Tarifa estándar por revisión. | calculateCost(): Retorna la tarifa base sin modificaciones. | Modela el cobro de una revisión médica general de rutina. |
| **Vaccination** | Concreta | baseFee: Costo de aplicación. vaccineType (VaccineType): Biológico. | calculateCost(): Suma la tarifa de aplicación al costo de la vacuna. | Calcula el precio total dependiendo del biológico específico suministrado. |
| **Surgery** | Concreta | hours: Duración en quirófano. complexityLevel (SurgeryComplexity): Nivel de riesgo. | calculateCost(): Multiplica horas por un factor de riesgo. | Calcula el precio de procedimientos complejos basándose en el tiempo y el riesgo médico. |
| **Receipt** | Concreta | totalAmount: Monto final. issueDate: Fecha de expedición. | generateTicket(): Imprime el desglose de servicios y total. | Consolida el cobro final de una cita en un registro contable inmutable. |
| **ClinicController** | Concreta | clientPets (HashMap): Dueños y sus mascotas. waitingRoom (Queue): Pacientes en espera. | scheduleAppointment(): Valida disponibilidad y agenda. calculateVetIncome(): Suma ingresos de un médico. | Coordina las operaciones, maneja búsquedas $O(1)$ y procesa peticiones de los menús sin acoplar la vista. |
| **Menu** | Interfaz | (No aplica) | displayOptions(): Muestra opciones en consola. readUserInput(): Lee selección del usuario. | Estandariza la forma en que los distintos menús interactúan con la consola de texto. |
| **AdminMenu** | Concreta | controller (ClinicController): Gestor general. scanner: Lector de consola. | displayOptions(): Imprime opciones exclusivas de administración. | Proporciona la interfaz de consola para usuarios con permisos administrativos. |
| **ReceptionistMenu** | Concreta | controller (ClinicController): Gestor general. scanner: Lector de consola. | displayOptions(): Imprime opciones operativas de mostrador. | Proporciona la interfaz de consola para la operación diaria en recepción. |
| **AppointmentStatus** | Enum | **SCHEDULED**, **CANCELED**, **COMPLETED** | (Sin métodos adicionales) | Restringe los estados posibles de una cita médica, evitando errores tipográficos en el flujo lógico (Regla 3). |
| **VaccineType** | Enum | **RABIES**, **PARVOVIRUS** Atributo interno: cost: Precio del biológico. | (Sin métodos adicionales) | Encapsula los biológicos y su precio fijo, ahorrando memoria al no instanciar múltiples objetos por vacuna. |
| **SurgeryComplexity** | Enum | **LOW**, **MEDIUM**, **HIGH** Atributo interno: riskMultiplier: Factor matemático. | (Sin métodos adicionales) | Clasifica el nivel de riesgo quirúrgico, facilitando el cálculo polimórfico del costo en el servicio. |
| **EmployeeRole** | Enum | **ADMINISTRATOR**, **RECEPTIONIST**, **VETERINARIAN** | (Sin métodos adicionales) | Define los niveles de acceso del personal para que el sistema decida qué menú instanciar. |
| **ShiftType** | Enum | **MORNING**, **EVENING**, **NIGHT** | (Sin métodos adicionales) | Clasifica el horario de trabajo del personal sin la complejidad de registrar horas exactas por empleado. |

### **2\. Clases Descartadas**

**Vaccine (Vacuna):** Se descartó como clase entidad porque no se requiere gestión de inventarios, proveedores o caducidades; un Enum (VaccineType) con un atributo de costo.

**Shift (Turno):** Se descartó porque el sistema no registra el control de asistencia con horas dinámicas de entrada/salida; un Enum (ShiftType) clasifica los horarios.

### **3\. Objetos de Ejemplo**

**1\. Objeto de tipo Client (Dueño)**

* id: 1  
* firstName: "Moisés Gerónimo"  
* lastName: "Torres González"  
* phone: "9215690297"  
* email: "mt242193@gmail.com"  
* address: "Minatitlán, Veracruz"

**2\. Objeto de tipo Dog (Mascota)**

* id: 101  
* name: "Hercules"  
* age: 3  
* weight: 12.5  
* owner: \[Referencia a Client\]  
* history: \[Objeto MedicalHistory\]  
* breed: "Mestizo"

**3\. Objeto de tipo Appointment (Cita)**

* dateTime: 2026-11-05T10:00:00  
* patient: \[Referencia a Dog\]  
* vet: \[Referencia a Veterinarian\]  
* room: \[Referencia a ConsultingRoom\]  
* services: \[Lista con un objeto GeneralConsultation\]  
* status: AppointmentStatus.SCHEDULED 

### **3\. Notas generales**

Me basé en cumplir lo solicitado en el documento y en cumplir las reglas del negoció, solo que decidí agregar un añadido. Decidí estructurar el personal mediante la clase abstracta Employee (heredera de Person), lo que me permitió agregar la gestión de salarios y cargos (esto no está solicitado en el proyecto). Esto lo integré con un diseño de menús basados en roles utilizando una interfaz polimórfica que adapta dinámicamente la terminal según el usuario activo; de esta manera, configuré al administrador para gestionar el personal y los sueldos, dejando al recepcionista las operaciones cotidianas de mostrador como el registro de citas y clientes. ¿Por qué hice esto?, la razón principal fue que vi que el administrador (admin) no tenía mucho sentido de estar en el programa.

### **4\. Uso de IA**

Utilicé la IA como un tutor que me ayudó a diseñar las clases y a descartar varias ideas erróneas que tenía. Me enfoque en dar propuestas basadas en la descripción del problema y las reglas del negocio y la IA evaluaba esas propuestas, reforcé mis conocimientos sobre los conceptos de composición de agregación en java. También use la IA para dar formato a este documento y hacer mis clases y métodos en el idioma inglés.  
