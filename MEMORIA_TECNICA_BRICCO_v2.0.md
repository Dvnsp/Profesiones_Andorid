
Memoria Técnica de Desarrollo de Software
Proyecto Final: Bricco

Autor: Duvan Vargas
Módulo Formativo: Proyecto Final - Desarrollo de Aplicaciones Multiplataforma (DAM)
Versión del Documento: 2.0
Fecha: 17 de diciembre de 2025

----------------------------------------------------------------------------------------------------

2. Control de documentación

El control de versiones de este documento se gestiona a través de la siguiente tabla de historial, que registra los cambios significativos realizados en cada iteración.

Historial de Versiones:
Versión | Fecha                     | Autor        | Cambios Realizados
1.0     | 17 de diciembre de 2025   | Duvan Vargas | Creación inicial del documento y descripción de la arquitectura.
2.0     | 17 de diciembre de 2025   | Duvan Vargas | Expansión completa de todas las secciones, adición de justificaciones técnicas, manual de usuario detallado y resolución económica. Documento finalizado.

Estado del Documento: Final

----------------------------------------------------------------------------------------------------

3. Índice

4. Introducción
   4.1. Contexto del Proyecto
   4.2. Motivación y Declaración del Problema
   4.3. Usuarios Destino
   4.4. Propuesta de Valor
   4.5. Objetivos Generales

5. Descripción del proyecto
   5.1. Descripción Detallada de la Aplicación
   5.2. Alcance Funcional
   5.3. Evolución del Producto
   5.4. Casos de Uso Principales
   5.5. Limitaciones y Asunciones

6. Requerimientos
   6.1. Requisitos Funcionales
   6.2. Requisitos No Funcionales
   6.3. Objetivos y Metodología de Desarrollo

7. Arquitectura
   7.1. Arquitectura General del Sistema
   7.2. Justificación de las Decisiones Arquitectónicas
   7.3. Interacción de Componentes y Flujos de Datos
   7.4. Topología del Sistema
   7.5. Librerías Externas y Licenciamiento

8. Producto – Desarrollo
   8.1. Características Técnicas
   8.2. Estrategia de Versionado
   8.3. Gestión de Permisos
   8.4. Principios de Diseño UI/UX y Paleta de Colores

9. Producto – Manual de Usuario
   9.1. Guía Paso a Paso de la Aplicación
   9.2. Escenarios de Uso Típicos

10. Producto – Desarrollo de la Arquitectura
    10.1. Estructura de Paquetes y Componentes Internos
    10.2. Estrategia de Persistencia de Datos
    10.3. Flujo de la Lógica de Negocio
    10.4. Consideraciones de Escalabilidad

11. Resolución económica
    11.1. Metodología de Estimación
    11.2. Desglose de Tareas de Desarrollo
    11.3. Cálculo de Costes del Proyecto

12. Extras
    12.1. Conclusiones Finales

----------------------------------------------------------------------------------------------------

4. Introducción

4.1. Contexto del Proyecto
En la era digital actual, la economía de servicios bajo demanda (gig economy) ha transformado la manera en que los consumidores acceden a bienes y servicios. Sin embargo, el sector de los oficios tradicionales, como la fontanería, la electricidad o la carpintería, a menudo permanece fragmentado y dependiente de métodos anticuados como el boca a boca o los directorios de páginas amarillas. Existe una clara oportunidad de mercado para desarrollar soluciones tecnológicas que modernicen y simplifiquen la conexión entre profesionales de oficios y clientes potenciales.

4.2. Motivación y Declaración del Problema
El principal problema que Bricco busca resolver es la ineficiencia y la falta de confianza en el proceso de contratación de servicios profesionales. Los clientes enfrentan dificultades para encontrar profesionales cualificados, comparar precios de manera transparente y verificar la fiabilidad de un proveedor. Por su parte, los profesionales independientes carecen de herramientas de marketing digital efectivas para alcanzar una base de clientes más amplia. Bricco nace para solventar esta brecha, ofreciendo un ecosistema digital seguro y centralizado.

4.3. Usuarios Destino
El proyecto define dos perfiles de usuario principales:
- Cliente Final: Propietarios de viviendas, administradores de fincas o pequeñas empresas que necesitan contratar un servicio de manera puntual. Buscan conveniencia, seguridad y transparencia en el proceso.
- Profesional (Alcance Futuro): Trabajadores autónomos o pequeñas empresas de servicios que desean aumentar su visibilidad, gestionar sus reservas de forma eficiente y construir una reputación digital.

4.4. Propuesta de Valor
La propuesta de valor de Bricco es ofrecer una plataforma móvil fiable, intuitiva y completa que simplifica y asegura todo el ciclo de vida de la contratación de un servicio, desde el descubrimiento del profesional hasta la confirmación de la reserva. Para el cliente, Bricco es sinónimo de conveniencia y confianza. Para el profesional, representa una oportunidad de crecimiento y digitalización de su negocio.

4.5. Objetivos Generales
Los objetivos estratégicos definidos para la consecución de este proyecto son los siguientes:
- OG-01: Diseñar y desarrollar una aplicación nativa para Android que sea robusta, eficiente y escalable.
- OG-02: Implementar un sistema seguro de gestión de usuarios, incluyendo registro y autenticación.
- OG-03: Proporcionar una interfaz de usuario clara e intuitiva que permita una navegación fluida y una experiencia de usuario satisfactoria.
- OG-04: Desarrollar un catálogo de servicios dinámico que permita a los usuarios explorar y seleccionar profesiones.
- OG-05: Construir un flujo de reserva de servicios completo, desde la selección hasta la confirmación final.

----------------------------------------------------------------------------------------------------

5. Descripción del proyecto

5.1. Descripción Detallada de la Aplicación
Bricco es una aplicación móvil nativa para Android que funciona como un mercado de servicios locales. Permite a los usuarios finales crear una cuenta personal, iniciar sesión de forma segura y explorar un catálogo de profesiones. Cada profesión listada en la aplicación se presenta con un icono representativo y un nombre. Al seleccionar una profesión, el usuario puede acceder a una vista detallada con información adicional. La funcionalidad principal de la aplicación es el sistema de reservas, que guía al usuario a través de los pasos necesarios para solicitar un servicio, culminando en una pantalla de confirmación que resume la transacción.

5.2. Alcance Funcional
El alcance de la versión actual del producto incluye las siguientes funcionalidades:
- Gestión de Cuentas de Usuario: Creación de nuevas cuentas y autenticación de usuarios existentes.
- Catálogo de Profesiones: Visualización de un listado de servicios profesionales.
- Detalles del Servicio: Acceso a una pantalla con información específica de cada servicio.
- Sistema de Reservas: Flujo completo para solicitar un servicio, aunque sin procesamiento de pagos.
- Confirmación de Reserva: Pantalla de confirmación con los detalles de la reserva efectuada.
- Gestión Administrativa: Funcionalidad interna para añadir nuevas profesiones al catálogo.

Funcionalidades fuera del alcance actual:
- No se incluye un sistema de pago en la aplicación.
- No existe un módulo para que los profesionales gestionen sus perfiles o disponibilidad.
- No hay un sistema de valoraciones o reseñas de los servicios.
- No se implementa comunicación en tiempo real (chat) entre cliente y profesional.

5.3. Evolución del Producto
La idea inicial del proyecto era crear un simple directorio digital de profesionales. Sin embargo, durante la fase de análisis y diseño, se identificó que un simple listado no aportaba suficiente valor diferencial. El concepto evolucionó para convertirse en una plataforma transaccional, incorporando la gestión de usuarios para ofrecer una experiencia personalizada y un flujo de reserva completo. Esta evolución transformó el proyecto de un mero directorio informativo a una herramienta de servicio funcional, sentando las bases para futuras expansiones como la gestión de pagos y la comunicación en tiempo real.

5.4. Casos de Uso Principales
- CU-01: Registro de un nuevo usuario. Un visitante de la aplicación decide crear una cuenta. Rellena el formulario de registro con sus datos personales y, tras la validación, el sistema crea su perfil y le permite acceder.
- CU-02: Búsqueda y selección de un servicio. Un usuario autenticado navega por el catálogo de profesiones en la pantalla principal, utiliza filtros (si estuvieran disponibles) y selecciona un servicio de su interés para ver más detalles.
- CU-03: Realización de una reserva. Tras consultar los detalles de un servicio, el usuario decide contratarlo. Inicia el proceso de reserva, completa la información requerida (fecha, hora, descripción del trabajo) y confirma la solicitud, recibiendo una confirmación en pantalla.

5.5. Limitaciones y Asunciones
- Limitación Técnica: La aplicación en su estado actual opera de manera autónoma con una base de datos SQLite local. No se conecta a un servidor backend, lo que implica que los datos son estáticos o gestionados localmente.
- Asunción Funcional: Se asume que los datos de las profesiones y los profesionales son veraces y son introducidos por un administrador a través de las funcionalidades internas de la aplicación.
- Limitación de Alcance: El producto es un prototipo funcional completo (MVP - Minimum Viable Product) centrado en el flujo del cliente. El lado del proveedor del servicio es una futura línea de trabajo.

----------------------------------------------------------------------------------------------------

6. Requerimientos

6.1. Requisitos Funcionales
- RF-01: Gestión de Cuentas de Usuario.
  - Descripción: El sistema debe permitir a los usuarios crear una cuenta nueva y acceder a ella mediante un proceso de inicio de sesión.
  - Justificación: Es fundamental para ofrecer una experiencia personalizada y segura, así como para asociar las reservas a un cliente específico.
  - Criterios de Aceptación: Un usuario puede rellenar un formulario de registro. El sistema valida los datos y crea un nuevo registro en la base de datos. El usuario puede iniciar sesión con sus credenciales.

- RF-02: Catálogo de Profesiones.
  - Descripción: La aplicación debe mostrar una lista o cuadrícula de las profesiones disponibles.
  - Justificación: Constituye la funcionalidad central de descubrimiento, permitiendo a los usuarios explorar la oferta de servicios.
  - Criterios de Aceptación: La pantalla principal muestra todas las profesiones almacenadas en la base de datos, cada una con su nombre e icono.

- RF-03: Flujo de Reserva de Servicio.
  - Descripción: El usuario debe poder seleccionar un servicio, proceder a una pantalla de reserva y confirmar su solicitud.
  - Justificación: Es el objetivo final del usuario y la principal propuesta de valor de la aplicación.
  - Criterios de Aceptación: El usuario selecciona un servicio, completa un formulario de reserva y, al confirmar, es dirigido a una pantalla de éxito.

- RF-04: Uso de la Cámara.
  - Descripción: La aplicación debe ser capaz de acceder a la cámara del dispositivo.
  - Justificación: Este requisito se establece para dar soporte a la librería de escaneo de códigos QR (ZXing), permitiendo futuras integraciones como la lectura de perfiles de profesionales o la aplicación de descuentos.
  - Criterios de Aceptación: La aplicación puede invocar una interfaz de cámara para escanear un código.

6.2. Requisitos No Funcionales
- RNF-01: Rendimiento.
  - Descripción: La aplicación debe ser fluida y responsiva. El tiempo de carga inicial no debe superar los 3 segundos en un dispositivo de gama media. Las animaciones y el desplazamiento por listas deben mantenerse a 60 fotogramas por segundo (FPS) para evitar interrupciones visuales (jank).
- RNF-02: Seguridad.
  - Descripción: Las contraseñas de los usuarios no deben almacenarse en texto plano. Se debe aplicar un mecanismo de hash para proteger las credenciales en la base de datos local. La aplicación no debe registrar información sensible en logs públicos.
- RNF-03: Usabilidad.
  - Descripción: La interfaz debe ser intuitiva y adherirse a los principios de diseño de Material Design de Google. Un usuario sin experiencia previa debería ser capaz de completar una reserva en menos de 90 segundos desde que abre la aplicación.
- RNF-04: Mantenibilidad.
  - Descripción: El código fuente debe estar claramente estructurado, siguiendo la arquitectura en capas definida. Se utilizarán los convenios de codificación de Kotlin y se añadirá documentación en el código para las lógicas complejas, facilitando futuras modificaciones y la incorporación de nuevos desarrolladores.

6.3. Objetivos y Metodología de Desarrollo
- Enfoque de Desarrollo: Se ha adoptado un enfoque de desarrollo iterativo e incremental, inspirado en las metodologías ágiles como Scrum. El proyecto se descompone en pequeñas funcionalidades que se desarrollan, prueban e integran en ciclos cortos.
- Herramientas y Tecnologías:
  - IDE: Android Studio
  - Lenguaje: Kotlin
  - Sistema de Construcción: Gradle
  - Base de Datos: SQLite
  - Control de Versiones: Git
- Estrategia de Control de Versiones: Se utiliza Git como sistema de control de versiones. Se recomienda una estrategia de ramificación como GitFlow, con una rama `main` para las versiones estables, una rama `develop` para la integración continua y ramas `feature` para el desarrollo de nuevas funcionalidades de forma aislada.

----------------------------------------------------------------------------------------------------

7. Arquitectura

7.1. Arquitectura General del Sistema
La aplicación Bricco se ha construido sobre una arquitectura en capas (Layered Architecture) que opera de forma monolítica en el dispositivo cliente. Esta arquitectura desacopla las diferentes responsabilidades lógicas del software en tres niveles principales:
- Capa de Presentación (Presentation Layer): Responsable de la interfaz de usuario (UI) y la experiencia de usuario (UX). Contiene las Activities, Fragments, Adapters y gestiona las interacciones del usuario.
- Capa de Datos (Data Layer): Responsable de la gestión y persistencia de los datos. Abstrae las fuentes de datos del resto de la aplicación a través del patrón Repositorio. Contiene el gestor de la base de datos (SQLite), los repositorios y, en un futuro, los clientes de red para APIs.
- Capa de Dominio (Domain Layer): Contiene la lógica de negocio y las entidades del modelo de datos. Es el corazón de la aplicación y es independiente de las otras capas. En este proyecto, está representada por las clases de entidad (`Usuario`, `Profesion`).

7.2. Justificación de las Decisiones Arquitectónicas
La elección de una arquitectura en capas se justifica por su simplicidad, claridad y su probada eficacia en la separación de conceptos (Separation of Concerns). Este enfoque facilita enormemente la mantenibilidad y la escalabilidad del proyecto.
- El uso del patrón Repositorio (`Repository Pattern`) es una decisión estratégica clave. Este patrón desacopla la capa de presentación y la lógica de negocio de la fuente de datos concreta (SQLite). Gracias a esto, en el futuro se podría reemplazar la base de datos local por una API RESTful sin necesidad de modificar el código de las Activities, simplemente cambiando la implementación del repositorio.
- La elección de Kotlin como lenguaje principal se debe a sus ventajas sobre Java para el desarrollo en Android: código más conciso, seguridad contra nulos (null safety) y soporte para programación funcional y corrutinas, lo que mejora la productividad y la calidad del software.

7.3. Interacción de Componentes y Flujos de Datos
Un flujo de datos típico, como la autenticación de un usuario, sigue esta secuencia:
1.  La `LoginActivity` (Capa de Presentación) captura las credenciales introducidas por el usuario.
2.  La Activity invoca un método en `UsuarioRepository` (Capa de Datos), pasándole las credenciales. No tiene conocimiento de cómo se validarán.
3.  `UsuarioRepository` ejecuta la lógica de validación. Para ello, solicita al `BriccoDbHelper` (Capa de Datos) que realice una consulta sobre la tabla `usuarios` en la base de datos SQLite.
4.  El `BriccoDbHelper` ejecuta la consulta SQL y devuelve un cursor o un objeto.
5.  El Repositorio procesa el resultado y lo devuelve a la `LoginActivity`.
6.  La `LoginActivity` actualiza la UI en función del resultado: navega a la pantalla principal si es exitoso o muestra un mensaje de error si falla.

7.4. Topología del Sistema
La topología del sistema es de cliente autónomo (standalone client). Toda la aplicación, incluyendo su lógica y almacenamiento, reside y se ejecuta en el dispositivo Android del usuario. No existe una comunicación con un servidor remoto. La base de datos SQLite actúa como la capa de persistencia local, funcionando como el "backend" del sistema en esta versión.

Esquema de Topología:
[ Dispositivo Móvil Android ]
  |
  +-- [ Aplicación Bricco ]
        |
        +-- [ Capa de Presentación (UI) ]
        |
        +-- [ Lógica de Negocio y Datos (Repositories, Managers) ]
        |
        +-- [ Base de Datos Local (SQLite) ]

7.5. Librerías Externas y Licenciamiento
La aplicación utiliza las siguientes librerías de terceros, licenciadas principalmente bajo Apache 2.0:
- AndroidX Core, AppCompat, Activity, ConstraintLayout: Componentes fundamentales de Android Jetpack para la compatibilidad y construcción de la UI.
- Material Components for Android: Proporciona elementos de UI que implementan Material Design, garantizando una estética moderna y consistente. Su uso se justifica para acelerar el desarrollo y ofrecer una UX de alta calidad.
- ZXing (Core y Embedded): Librería de código abierto para el escaneo de códigos de barras y QR. Se ha seleccionado por ser el estándar de facto en el ecosistema Android para esta funcionalidad.

----------------------------------------------------------------------------------------------------

8. Producto – Desarrollo

8.1. Características Técnicas
- Plataforma de Destino: Android
- Tipo de Aplicación: Nativa
- Lenguaje Principal: Kotlin
- Entorno de Desarrollo Integrado (IDE): Android Studio
- Sistema de Construcción (Build System): Gradle
- Arquitectura de CPU Soportada: ARM (armeabi-v7a, arm64-v8a), x86, x86_64

8.2. Estrategia de Versionado
La aplicación sigue una estrategia de versionado dual, como es estándar en Android:
- `versionCode`: Un número entero que se incrementa de forma monótona con cada nueva versión subida a la tienda de aplicaciones. La versión actual es `1`.
- `versionName`: Una cadena de texto visible para el usuario que sigue el estándar de Versionado Semántico (Semantic Versioning) `MAJOR.MINOR.PATCH`. La versión actual es `1.0`. Esto comunica la naturaleza de los cambios (cambios que rompen la compatibilidad, nuevas funcionalidades o correcciones de errores).

8.3. Gestión de Permisos
La aplicación requiere el siguiente permiso, declarado en `AndroidManifest.xml`:
- `android.permission.CAMERA`
- Justificación: Este permiso es indispensable para la funcionalidad de escaneo de códigos QR, implementada a través de la librería ZXing. Aunque esta función pueda ser secundaria en el flujo principal, está prevista para futuras mejoras como la identificación rápida de profesionales o la validación de ofertas. El permiso se solicita en tiempo de ejecución (runtime permission), asegurando que el usuario otorga su consentimiento explícito antes de que la aplicación acceda al hardware.

8.4. Principios de Diseño UI/UX y Paleta de Colores
El diseño de la aplicación se basa en los siguientes principios:
- Claridad y Simplicidad: La interfaz es limpia, sin elementos superfluos, para que el usuario pueda completar sus tareas de la forma más directa posible.
- Consistencia: Se utilizan patrones de diseño y componentes de Material Design para asegurar que la experiencia sea predecible y coherente en toda la aplicación.
- Feedback al Usuario: Todas las acciones del usuario (pulsar un botón, rellenar un formulario) van acompañadas de una respuesta visual (animaciones, mensajes, cambios de estado) que informa del resultado de la acción.

La paleta de colores corporativa ha sido seleccionada para transmitir profesionalidad y confianza:
- Color Primario (`#1565C0` - Azul): Utilizado para elementos de acción principales, la barra de herramientas y la marca. Evoca seriedad y confianza.
- Color Secundario (`#FFB300` - Ámbar): Empleado para elementos de acento, botones flotantes y para destacar acciones importantes. Aporta un contraste vibrante.
- Color de Superficie (`#F5F5F5` - Gris Claro): Color de fondo principal para las pantallas, proporcionando un lienzo limpio que mejora la legibilidad.

----------------------------------------------------------------------------------------------------

9. Producto – Manual de Usuario

9.1. Guía Paso a Paso de la Aplicación

- Pantalla de Carga (SplashActivity)
  - Descripción: Es la primera pantalla que ve el usuario al iniciar la aplicación. Muestra el logo de Bricco.
  - Acciones de Usuario: Ninguna. La pantalla es automática y transitoria.
  - Navegación: Después de 2 segundos, redirige automáticamente a la Pantalla de Bienvenida.

- Pantalla de Bienvenida (WelcomeActivity)
  - Descripción: Presenta la aplicación al usuario y sirve como portal de entrada.
  - Acciones de Usuario: El usuario puede pulsar el botón "Iniciar Sesión" o el botón "Registrarse".
  - Navegación: Pulsar "Iniciar Sesión" lleva a `LoginActivity`. Pulsar "Registrarse" lleva a `RegisterActivity`.

- Pantalla de Registro (RegisterActivity)
  - Descripción: Permite a los nuevos usuarios crear una cuenta.
  - Componentes: Campos de texto para nombre, email y contraseña. Botón "Crear Cuenta".
  - Acciones de Usuario: El usuario debe rellenar todos los campos y pulsar "Crear Cuenta".
  - Manejo de Errores: Si algún campo está vacío, se mostrará un mensaje indicando que es obligatorio. Si el email ya existe, se informará al usuario.
  - Navegación: Tras un registro exitoso, se redirige al usuario a `LoginActivity`.

- Pantalla de Inicio de Sesión (LoginActivity)
  - Descripción: Permite a los usuarios existentes acceder a su cuenta.
  - Componentes: Campos de texto para email y contraseña. Botón "Iniciar Sesión".
  - Acciones de Usuario: El usuario introduce sus credenciales y pulsa "Iniciar Sesión".
  - Manejo de Errores: Si las credenciales son incorrectas o los campos están vacíos, se muestra un mensaje de error descriptivo.
  - Navegación: Tras una autenticación exitosa, se navega a la Pantalla Principal (`MainActivity`).

- Pantalla Principal (MainActivity)
  - Descripción: Es el centro de la aplicación para usuarios autenticados. Muestra el catálogo de profesiones.
  - Componentes: Una cuadrícula o lista (`RecyclerView`) con las profesiones disponibles.
  - Acciones de Usuario: El usuario puede desplazarse por la lista y pulsar en cualquier profesión para ver sus detalles.
  - Navegación: Pulsar en un elemento de la lista navega a `ProfesionDetailActivity`.

- Pantalla de Detalle de Profesión (ProfesionDetailActivity)
  - Descripción: Muestra información detallada sobre el servicio seleccionado.
  - Componentes: Nombre del servicio, descripción, imagen y un botón "Reservar".
  - Acciones de Usuario: El usuario puede leer la información y pulsar "Reservar" para iniciar el proceso.
  - Navegación: Pulsar "Reservar" navega a `ReservationActivity`.

- Pantalla de Reserva (ReservationActivity)
  - Descripción: Formulario para que el usuario concrete los detalles de su reserva.
  - Componentes: Campos para fecha, hora y una descripción del trabajo a realizar. Botón "Confirmar Reserva".
  - Acciones de Usuario: El usuario rellena los detalles y confirma la reserva.
  - Navegación: Tras pulsar "Confirmar Reserva", se navega a `ReservationConfirmationActivity`.

- Pantalla de Confirmación de Reserva (ReservationConfirmationActivity)
  - Descripción: Informa al usuario de que su reserva ha sido procesada con éxito.
  - Componentes: Mensaje de confirmación y un resumen de la reserva. Botón para volver al inicio.
  - Acciones de Usuario: El usuario puede pulsar el botón para volver a la pantalla principal.
  - Navegación: El botón de acción devuelve al usuario a `MainActivity`.

9.2. Escenarios de Uso Típicos
- Escenario 1: Un nuevo usuario necesita un fontanero.
  1. Descarga e inicia Bricco.
  2. Desde la `WelcomeActivity`, pulsa "Registrarse".
  3. Completa el formulario en `RegisterActivity` y crea su cuenta.
  4. Es redirigido a `LoginActivity`, donde introduce sus nuevas credenciales.
  5. En `MainActivity`, localiza y pulsa sobre "Fontanería".
  6. En `ProfesionDetailActivity`, lee la descripción y pulsa "Reservar".
  7. En `ReservationActivity`, especifica el problema y la hora deseada, y confirma.
  8. Visualiza la pantalla `ReservationConfirmationActivity` y cierra la aplicación.

----------------------------------------------------------------------------------------------------

10. Producto – Desarrollo de la Arquitectura

10.1. Estructura de Paquetes y Componentes Internos
La estructura de paquetes del proyecto está organizada por funcionalidad para promover una alta cohesión y un bajo acoplamiento:
- `uvic.cat.comuvicdam_tf_202526dvargas.activity`: Contiene exclusivamente las clases `Activity`, que actúan como controladores de la UI.
- `uvic.cat.comuvicdam_tf_202526dvargas.adapter`: Alberga los adaptadores para `RecyclerViews`, responsables de vincular los conjuntos de datos con las vistas.
- `uvic.cat.comuvicdam_tf_202526dvargas.data`: Núcleo de la capa de datos. Incluye `BriccoDbHelper` y `UsuarioRepository`.
- `uvic.cat.comuvicdam_tf_202526dvargas.entities`: Define los modelos de datos (POJOs/POKOs) que representan las entidades de negocio.
- `uvic.cat.comuvicdam_tf_202526dvargas.singleton`: Contiene objetos singleton como `DataManager`, que gestionan el estado o los datos a nivel global de la aplicación.

10.2. Estrategia de Persistencia de Datos
La persistencia de datos se realiza mediante una base de datos SQLite gestionada por la clase `BriccoDbHelper`, que hereda de `SQLiteOpenHelper`.
- Creación de Tablas: En el método `onCreate`, se definen y ejecutan las sentencias SQL `CREATE TABLE` para las entidades `usuarios` y `profesiones`.
- Gestión de Versiones: El método `onUpgrade` contiene la lógica para migrar el esquema de la base de datos cuando se incrementa el número de versión, asegurando que los datos de los usuarios no se pierdan entre actualizaciones.
- Tablas Principales:
  - `usuarios`: Almacena la información de los usuarios registrados (ID, nombre, email, hash de contraseña).
  - `profesiones`: Almacena el catálogo de servicios (ID, nombre, descripción, ruta del icono).

10.3. Flujo de la Lógica de Negocio
La lógica de negocio no reside en las `Activities`, sino que está encapsulada en los `Repositories` y `Managers`.
- `UsuarioRepository` se encarga de toda la lógica relacionada con los usuarios: validación de credenciales, creación de nuevos usuarios y comprobación de duplicados.
- `DataManager` actúa como un repositorio para los datos de las profesiones. Es responsable de inicializar el catálogo de profesiones (posiblemente desde un recurso estático la primera vez que se ejecuta la aplicación) y de proporcionar la lista de profesiones a la capa de presentación.

10.4. Consideraciones de Escalabilidad
La arquitectura actual, aunque simple, está diseñada con la escalabilidad en mente.
- El patrón Repositorio es el pilar de la escalabilidad. Para migrar de una base de datos local a un servicio en la nube (backend con API REST), solo sería necesario modificar el contenido de los métodos de `UsuarioRepository` y `DataManager` para que realicen llamadas de red (usando librerías como Retrofit o Ktor) en lugar de consultas SQL. La capa de presentación no se vería afectada.
- El uso de un `ViewModel` (componente de Android Jetpack no presente actualmente) sería el siguiente paso lógico. Introducir un `ViewModel` entre las `Activities` y los `Repositories` mejoraría la gestión del estado de la UI y la gestión del ciclo de vida, haciendo la aplicación aún más robusta y escalable.

----------------------------------------------------------------------------------------------------

11. Resolución económica

11.1. Metodología de Estimación
La estimación económica del proyecto se ha realizado utilizando un enfoque bottom-up. Se ha desglosado el proyecto en sus tareas de desarrollo fundamentales y se ha estimado el tiempo en horas necesario para completar cada una. A continuación, se ha aplicado una tarifa por hora estándar del mercado para un desarrollador de perfil junior-medio.

11.2. Desglose de Tareas de Desarrollo
Se ha establecido una tarifa de 30,00 € por hora de desarrollo.

Tarea                               | Descripción                                                              | Horas Estimadas
------------------------------------|--------------------------------------------------------------------------|-----------------
Análisis y Diseño                   | Definición de requisitos, diseño de la arquitectura y modelo de datos.   | 15
Configuración del Proyecto          | Creación del proyecto, configuración de Gradle, librerías y DB local.    | 10
Módulo de Autenticación             | Desarrollo de las pantallas y lógica para registro e inicio de sesión.   | 20
Catálogo de Servicios               | Creación del RecyclerView, adapter y pantalla de detalle.                | 25
Flujo de Reserva                    | Desarrollo de las pantallas de reserva y confirmación.                   | 20
Integración de Cámara (QR)          | Implementación de la librería ZXing y la lógica de escaneo.              | 10
Pruebas y Depuración                | Creación de tests unitarios y de UI, y corrección de errores.            | 20
Documentación Técnica               | Redacción de la memoria técnica completa del proyecto.                   | 15
**Total Horas de Desarrollo**       |                                                                          | **135**

11.3. Cálculo de Costes del Proyecto
- Coste Base de Desarrollo:
  135 horas * 30,00 €/hora = 4.050,00 €

- Contingencia (15%):
  Se añade un margen de contingencia del 15% para cubrir imprevistos, tareas no planificadas o desviaciones en la estimación.
  4.050,00 € * 0,15 = 607,50 €

- Coste Total Estimado del Proyecto:
  4.050,00 € + 607,50 € = **4.657,50 €**

El coste total estimado para el desarrollo de la versión 1.0 de la aplicación Bricco asciende a **4.657,50 euros**.

----------------------------------------------------------------------------------------------------

12. Extras

12.1. Conclusiones Finales
El proyecto Bricco ha cumplido con éxito todos los objetivos propuestos, resultando en una aplicación Android funcional, robusta y con una base arquitectónica sólida. La aplicación resuelve un problema real del mercado de manera efectiva y presenta una experiencia de usuario pulida. La arquitectura seleccionada no solo es adecuada para el alcance actual del proyecto, sino que también sienta las bases para futuras expansiones, garantizando la viabilidad a largo plazo del producto. El desarrollo ha seguido las mejores prácticas de la industria, resultando en un software de alta calidad técnica.

----------------------------------------------------------------------------------------------------
Bricco v2.0 - 17 de diciembre de 2025 - Página X de Y
