
# **Memoria Técnica – Proyecto Final: Bricco**

**Autor:** Duvan Vargas  
**Asignatura:** Proyecto Final - Desarrollo de Aplicaciones Multiplataforma (DAM)  
**Fecha:** 17 de diciembre de 2025

---

## **2. Control de documentación**

| **Autor**     | **Versión** | **Fecha**                 | **Estado** |
|---------------|-------------|---------------------------|------------|
| Duvan Vargas  | 1.0         | 17 de diciembre de 2025   | Final      |

---

## **3. Índice**

1.  **Introducción**
    *   1.1. Idea General
    *   1.2. Problema y Solución
2.  **Descripción del Proyecto**
    *   2.1. Descripción Detallada
    *   2.2. Funcionamiento General
3.  **Requerimientos**
    *   3.1. Requisitos Funcionales
    *   3.2. Requisitos No Funcionales
    *   3.3. Objetivo y Metodología
4.  **Arquitectura**
    *   4.1. Arquitectura General
    *   4.2. Esquema de Componentes
    *   4.3. Validación de Usuarios
    *   4.4. Topología del Sistema
    *   4.5. Licencias
5.  **Producto – Desarrollo**
    *   5.1. Características Técnicas
    *   5.2. Identificador y Versionado
    *   5.3. Permisos Requeridos
    *   5.4. Tema Visual
6.  **Producto – Manual de Usuario**
    *   6.1. Flujo de Navegación
    *   6.2. Descripción de Pantallas
7.  **Producto – Desarrollo de la Arquitectura**
    *   7.1. Componentes Internos
    *   7.2. Librerías Externas
    *   7.3. Persistencia de Datos
8.  **Resolución Económica**

---

## **1. Introducción**

### **1.1. Idea General**

Bricco es una aplicación móvil nativa para la plataforma Android, diseñada para actuar como un intermediario digital entre usuarios que requieren servicios profesionales de oficios (como fontanería, electricidad, etc.) y los proveedores de dichos servicios.

### **1.2. Problema y Solución**

El proyecto aborda la dificultad recurrente que enfrentan las personas para encontrar profesionales de oficios fiables y disponibles en su área local. Bricco propone una solución centralizada: una plataforma intuitiva donde los usuarios pueden registrarse, explorar un catálogo de servicios, visualizar los perfiles de los profesionales y gestionar la reserva de sus servicios de manera directa y eficiente.

---

## **2. Descripción del Proyecto**

### **2.1. Descripción Detallada**

Bricco se materializa como una aplicación Android que permite a los usuarios, tras un proceso de registro y autenticación, acceder a una lista categorizada de profesiones. Cada categoría ofrece información detallada sobre el servicio. El usuario tiene la capacidad de seleccionar un servicio y proceder con una reserva, culminando el proceso en una pantalla de confirmación. La aplicación también contempla una funcionalidad administrativa para añadir nuevas profesiones al sistema.

### **2.2. Funcionamiento General**

El flujo principal de la aplicación comienza con una pantalla de bienvenida (`SplashActivity`) que dirige al usuario a un portal de bienvenida. Desde allí, puede optar por iniciar sesión (`LoginActivity`) si ya posee una cuenta, o registrarse (`RegisterActivity`) como nuevo usuario. Una vez autenticado, el usuario accede a la pantalla principal (`MainActivity`), que presenta el catálogo de servicios. Puede navegar para ver los detalles de una profesión (`ProfesionDetailActivity`), iniciar una reserva (`ReservationActivity`) y finalmente, ver la confirmación de la misma (`ReservationConfirmationActivity`).

---

## **3. Requerimientos**

### **3.1. Requisitos Funcionales**

*   **RF-01:** El sistema debe permitir el registro de nuevos usuarios mediante un formulario.
*   **RF-02:** El sistema debe permitir la autenticación de usuarios existentes.
*   **RF-03:** La aplicación debe mostrar una pantalla principal con un listado de las profesiones disponibles.
*   **RF-04:** Al seleccionar una profesión, la aplicación debe mostrar una pantalla con información detallada.
*   **RF-05:** El usuario debe poder iniciar un proceso de reserva para un servicio.
*   **RF-06:** El sistema debe mostrar una confirmación visual tras completar una reserva.
*   **RF-07:** La aplicación debe contar con una función para añadir nuevas profesiones a la base de datos.
*   **RF-08:** La aplicación debe poder utilizar la cámara del dispositivo.

### **3.2. Requisitos No Funcionales**

*   **RNF-01:** La aplicación debe ser compatible con dispositivos Android desde la versión 7.0 (Nougat, API 24) en adelante.
*   **RNF-02:** La interfaz de usuario debe ser intuitiva y seguir las guías de diseño de Material Design.
*   **RNF-03:** Los datos de usuario y profesiones deben persistir localmente en el dispositivo.
*   **RNF-04:** La aplicación debe tener un tiempo de respuesta rápido en las interacciones del usuario.
*   **RNF-05:** El código fuente debe estar estructurado y documentado para facilitar su mantenimiento.

### **3.3. Objetivo y Metodología**

El objetivo principal es desarrollar una aplicación Android completamente funcional que cumpla con todos los requisitos definidos. El desarrollo se ha abordado siguiendo una metodología iterativa, cercana a los principios Agile, permitiendo la construcción y validación de funcionalidades en ciclos cortos de desarrollo.

---

## **4. Arquitectura**

### **4.1. Arquitectura General**

La aplicación se ha diseñado siguiendo una arquitectura en capas que separa claramente las responsabilidades del software:

1.  **Capa de Presentación (UI):** Compuesta por `Activities`, `Fragments` y `Adapters`. Es responsable de dibujar la interfaz de usuario y gestionar las interacciones del usuario.
2.  **Capa de Datos:** Formada por `Repositories` y `Data-Helpers`. Abstrae el origen de los datos y centraliza la lógica de acceso a estos, ya sea desde una base de datos local o una API remota (aunque en este proyecto solo se usa local).
3.  **Capa de Dominio (Modelo):** Contiene las `Entities`, que son las representaciones de los objetos de datos (e.g., `Usuario`, `Profesion`).

### **4.2. Esquema de Componentes**

El flujo de datos e interacciones sigue el siguiente esquema:

`Activity (UI) -> Repository/Manager -> BriccoDbHelper (Database) -> Entity (Model)`

*   Una `Activity` (e.g., `LoginActivity`) solicita la validación de un usuario.
*   La petición es gestionada por el `UsuarioRepository`.
*   El `UsuarioRepository` utiliza `BriccoDbHelper` para ejecutar una consulta SQL en la base de datos SQLite.
*   La base de datos devuelve datos que pueden ser mapeados a una entidad `Usuario`.

### **4.3. Validación de Usuarios**

La validación se realiza localmente. Cuando un usuario introduce sus credenciales en `LoginActivity`, estas se pasan al `UsuarioRepository`, que consulta la tabla `usuarios` en la base de datos SQLite para verificar su existencia y corrección.

### **4.4. Topología del Sistema**

La topología es de cliente autónomo (standalone). Toda la lógica de negocio y el almacenamiento de datos residen en el dispositivo cliente. No hay comunicación con un servidor backend externo; la base de datos SQLite actúa como la capa de persistencia local.

### **4.5. Licencias**

El código del proyecto es propietario. Las librerías de terceros utilizadas se distribuyen principalmente bajo la licencia **Apache 2.0**, una licencia de software libre permisiva.

---

## **5. Producto – Desarrollo**

### **5.1. Características Técnicas**

*   **Plataforma:** Android Nativo
*   **Lenguaje de Programación:** Kotlin
*   **Entorno de Desarrollo:** Android Studio
*   **Sistema de Build:** Gradle

### **52. Identificador y Versionado**

*   **Application ID:** `uvic.cat.comuvicdam_tf_202526dvargas`
*   **Versión de Código:** `1`
*   **Versión de Nombre:** `1.0`
*   **API Mínima Soportada (minSdk):** `24` (Android 7.0 Nougat)
*   **API de Destino (targetSdk):** `36`

### **5.3. Permisos Requeridos**

La aplicación solicita el siguiente permiso en su `AndroidManifest.xml`:

*   `android.permission.CAMERA`: Requerido para funcionalidades de escaneo de códigos QR, a través de la librería ZXing.

### **5.4. Tema Visual**

La aplicación utiliza un tema personalizado (`Theme.Comuvicdam_tf_202526Dvargas`) que hereda de `Theme.Material3.DayNight.NoActionBar`. La paleta de colores principal es:

*   **primaryColor:** `#1565C0` (Azul)
*   **secondaryColor:** `#FFB300` (Ámbar)
*   **surfaceColor:** `#F5F5F5` (Gris claro)

---

## **6. Producto – Manual de Usuario**

### **6.1. Flujo de Navegación**

`Splash` -> `Welcome` -> (`Login` | `Register`) -> `Main (Listado)` -> `Detail` -> `Reservation` -> `Confirmation`

### **6.2. Descripción de Pantallas**

*   **SplashActivity:** Pantalla de carga inicial. Se muestra durante un breve periodo y redirige automáticamente a `WelcomeActivity`.
*   **WelcomeActivity:** Da la bienvenida al usuario y ofrece dos acciones principales: "Iniciar Sesión" (navega a `LoginActivity`) y "Registrarse" (navega a `RegisterActivity`).
*   **LoginActivity:** Formulario para que usuarios existentes introduzcan sus credenciales. Tras una validación exitosa, navega a `MainActivity`.
*   **RegisterActivity:** Formulario para la creación de una nueva cuenta de usuario. Tras un registro exitoso, navega a `LoginActivity`.
*   **MainActivity:** Pantalla principal para usuarios autenticados. Muestra un catálogo de profesiones. Permite la navegación a la pantalla de detalle.
*   **ProfesionDetailActivity:** Muestra información extendida sobre un servicio o profesión seleccionada. Contiene un botón para iniciar el proceso de reserva.
*   **ReservationActivity:** Pantalla donde el usuario finaliza los detalles de la reserva del servicio.
*   **ReservationConfirmationActivity:** Pantalla final del flujo, que confirma al usuario que la reserva se ha completado con éxito.
*   **AddProfesionActivity:** Pantalla con un formulario destinado a añadir nuevas profesiones a la base de datos.

---

## **7. Producto – Desarrollo de la Arquitectura**

### **7.1. Componentes Internos**

*   **`uvic.cat.comuvicdam_tf_202526dvargas.activity`**: Contiene todas las `Activity` de la aplicación, responsables de la capa de vista.
*   **`uvic.cat.comuvicdam_tf_202526dvargas.adapter`**: Contiene los adaptadores para las vistas de tipo `RecyclerView`.
*   **`uvic.cat.comuvicdam_tf_202526dvargas.data`**: Incluye `BriccoDbHelper` (gestor de la DB) y `UsuarioRepository` (lógica de acceso a datos de usuario).
*   **`uvic.cat.comuvicdam_tf_202526dvargas.entities`**: Define los modelos de datos `Profesion.java` y `Usuario.java`.
*   **`uvic.cat.comuvicdam_tf_202526dvargas.singleton`**: Contiene los gestores de estado global como `DataManager` y `CarritoManager`.

### **7.2. Librerías Externas**

El proyecto se apoya en las siguientes librerías externas, gestionadas a través de Gradle:

*   **AndroidX (androidx.core, androidx.appcompat, androidx.activity, androidx.constraintlayout):** Librerías base de Jetpack para retrocompatibilidad, componentes de UI y gestión del ciclo de vida.
*   **Material Components (com.google.android.material):** Provee componentes de UI que implementan las guías de Material Design.
*   **ZXing (com.journeyapps:zxing-android-embedded, com.google.zxing:core):** Librería para la generación y escaneo de códigos de barras y QR. Se utiliza para la funcionalidad de la cámara.
*   **JUnit & Espresso (junit, androidx.test.ext:junit, androidx.test.espresso:core):** Frameworks para la realización de tests unitarios y de instrumentación.

### **73. Persistencia de Datos**

La persistencia de datos se gestiona íntegramente a través de una base de datos **SQLite** local.

*   **Mecanismo:** La clase `BriccoDbHelper`, que hereda de `SQLiteOpenHelper`, se encarga de la creación del esquema de la base de datos, las tablas (`usuarios`, `profesiones`) y la gestión de las versiones.
*   **Acceso:** El acceso a la base de datos está abstraído por las clases `UsuarioRepository` y `DataManager`, que contienen los métodos CRUD (`Create`, `Read`, `Update`, `Delete`) y exponen una API sencilla al resto de la aplicación, ocultando la complejidad de las consultas SQL.

---

## **8. Resolución Económica**

Este apartado no es objeto de evaluación en la presente entrega del proyecto. La estimación de costes de desarrollo, mantenimiento y explotación se excluye del alcance de esta memoria.

---
<div style="text-align: right;">
<p>Página <ins>X</ins> de <ins>Y</ins></p>
<p>Bricco - v1.0 - 17 de diciembre de 2025</p>
</div>
