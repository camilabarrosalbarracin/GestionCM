# GestionCM

---

## Manual de Usuario para la prueba tecnica

---

### Pasos previos a la ejecución
Asegurarse de tener descargados/instalados los siguientes programas:

* Java: JDK 17
* SpringBoot: 3.2.1
* MySQL: 8.0.28
* Maven: 4.0.0

Ademas se aconseja tener un IDE de Java instalado (INTELLIJ recomendado), tener corriendo un servidor de MySQL y Maven Instalado.

---
### Cómo correr el proyecto
* Clonar el repositorio
* Crear una base de datos

```
Hostname: localhost
Puerto: 3306
Password:
User: root
```
* Crear un Schema y añadirlo con el nombre *"basedatosclinica"* en la base de datos
* Ejectutar el proyecto
* Ejectutar el *script* para cargar la base de datos (el mismo se encuentra más abajo)
* Ingresar en el navegador a:  
```
localhost:8080/inicio
```
---

### Navegación por la pagina segun caso de uso:

[![1.jpg](https://i.postimg.cc/J4nd7H35/1.jpg)](https://postimg.cc/hhk0pj5X)

* **Listado de Especialidades:** Seleccionar la opción “Especialidades” que se encuentra en la barra de navegación a la izquierda.
  A continuación se mostrarán por pantalla las especialidades listadas.

[![2.jpg](https://i.postimg.cc/pdxfTgbM/2.jpg)](https://postimg.cc/NKJ9CCjd)

* **Listado de Profesionales:** Seleccionar la opción “Profesionales” que se encuentra en la barra de navegación a la izquierda.
  A continuación se mostrarán por pantalla los profesionales listados con sus datos.

[![3.jpg](https://i.postimg.cc/mkcMJ4MC/3.jpg)](https://postimg.cc/4HgmHrWx)

* **Listado de turnos, pudiendo cancelar o modificarlos hasta una hora antes de hacerlo efectivo:** Seleccionar la opción “Turnos” que se encuentra en la barra de navegación a la izquierda, esto desplegará un submenú en la barra de navegación. Luego seleccionar “Ver todos los turnos”.
 
  A continuación se mostrarán todos los turnos que se encuentran cargados, habilitando solo la opción de “Cancelar” o “Modificar” a aquellos turnos que tengan una distancia temporal mayor a una hora, respecto de la hora de efectividad del mismo.
  
  En el caso de que desee cancelar el turno, deberá presionar el icono rojo del cesto, ubicado en la fila del turno correspondiente.

[![13.jpg](https://i.postimg.cc/fbwdZmmw/13.jpg)](https://postimg.cc/G45txBXN)

  En el caso de que se desee modificar el turno, deberá presionar el icono azul del lápiz, ubicado en la fila del turno correspondiente.
  Posterior a esta selección, se redigirá a una pantalla que permite la modificación de este turno en sus respectivos campos. Una vez finalizada la modificación se deberá seleccionar “Guardar Cambios”, lo que hará que esta sea efectiva. 

[![656231.jpg](https://i.postimg.cc/KzSFTSrQ/656231.jpg)](https://postimg.cc/670DsPPG)

* **Consulta de turnos por paciente (historial de visitas):** Seleccionar la opción “Consulta turnos por paciente” que se encuentra en la barra de navegación a la izquierda.
  A continuación se solicitará que se ingrese el número de afiliado del paciente (suponiendo que cada paciente posee un número de afiliado como identificador, dentro del sistema de la clínica). Posteriormente se mostrarán los turnos correspondientes al número ingresado. Caso contrario, se informará al usuario que no se encontraron turnos con el número ingresado.
  *(Para pruebas ingresar valores de numero de afiliado del 1-20 segun script)*

[![5.jpg](https://i.postimg.cc/FRH0b3HP/5.jpg)](https://postimg.cc/d7g7w7dd)

[![6.jpg](https://i.postimg.cc/tTvWBJ19/6.jpg)](https://postimg.cc/S28nsy7P)

[![155.jpg](https://i.postimg.cc/YqHDgyw8/155.jpg)](https://postimg.cc/w3wkY2Ms)


* **Listado de turnos por Especialidad:** Seleccionar la opción “Turnos” que se encuentra en la barra de navegación a la izquierda, esto desplegará un submenú en la barra de navegación. Luego seleccionar “Ver turnos por especialidad”. A continuación aparecerá un menú desplegable para elegir la especialidad deseada.  Posteriormente se mostrarán los turnos correspondientes a la especialidad. Caso contrario, se informará al usuario que no se encontraron turnos para esa especialidad.

[![7.jpg](https://i.postimg.cc/ZqcryV4B/7.jpg)](https://postimg.cc/B8j8r584)

[![8.jpg](https://i.postimg.cc/L61LVHmM/8.jpg)](https://postimg.cc/yk7D1CJf)

[![9.jpg](https://i.postimg.cc/66SCR7J0/9.jpg)](https://postimg.cc/dD27cV0k)
* **Listado de turnos por Profesional:** Seleccionar la opción “Turnos” que se encuentra en la barra de navegación a la izquierda, esto desplegará un submenú en la barra de navegación. Luego seleccionar “Ver turnos por profesional”.  A continuación aparecerá un menú desplegable para elegir el profesional deseado.  Posteriormente se mostrarán los turnos correspondientes al profesional. Caso contrario, se informará al usuario que no se encontraron turnos para ese profesional.

[![10.jpg](https://i.postimg.cc/Z5z371qv/10.jpg)](https://postimg.cc/56p6jKLf)

[![4.jpg](https://i.postimg.cc/L8q9PXSz/4.jpg)](https://postimg.cc/0zs1R9bQ)

[![11.jpg](https://i.postimg.cc/5yZvMrqG/11.jpg)](https://postimg.cc/T5JhVCsq)


---

### Aplicaciones utilizadas para el desarrollo del proyecto:
* **Postman:** para testeo de CRUD
* **Intelij:** entorno de desarrollo (IDE)
* **My SQL Workbench**
* **Framework spring:** gestión del proyecto
  * Dependencias:
    * Spring web (WEB)
    * Spring data JPA (SQL)
    * Lombok (DEVELOPER TOOLS)
    * Configuración: Java 17 / Maven
    * HTML : Bootstrap (herramientas de diseño web

---


### Modelo de datos:

[![7878.jpg](https://i.postimg.cc/Xq2Yd1vJ/7878.jpg)](https://postimg.cc/JHk8WqZV)

---
### Script para base de datos:
Se recomienda tener una app del estilo My SQL Workbench para la ejecucion de este script:
```
    -- CARGA DE BASE DE DATOS

    -- Insertar consultorios
    INSERT INTO basedatosclinica.consultorio (id, direccion_consultorio, numero_consultorio)
    VALUES (1, 'Rivadavia 2638', 1);
    INSERT INTO basedatosclinica.consultorio (id, direccion_consultorio, numero_consultorio)
    VALUES (2, 'Rivadavia 2638', 2);
    INSERT INTO basedatosclinica.consultorio (id, direccion_consultorio, numero_consultorio)
    VALUES (3, 'Rivadavia 2638', 3);


    -- Consultorio 1
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'LUNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'MARTES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'MIÉRCOLES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'JUEVES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'VIERNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (1, 'SÁBADO');
    
    -- Consultorio 2
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'LUNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'MARTES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'MIÉRCOLES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'JUEVES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'VIERNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (2, 'SÁBADO');
    
    -- Consultorio 3
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'LUNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'MARTES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'MIÉRCOLES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'JUEVES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'VIERNES');
    INSERT INTO basedatosclinica.consultorio_dias_abiertos (consultorio_id, dias_abiertos)
    VALUES (3, 'SÁBADO');
    
    
    -- Especialidades
    INSERT INTO basedatosclinica.especialidad (id, nombre_especialidad)
    VALUES 	(1, 'Clínica Médica'),
    (2, 'Dermatología'),
    (3, 'Pediatría'),
    (4, 'Cardiología'),
    (5, 'Neurología');
    
    -- Profesionalesturno
    INSERT INTO basedatosclinica.paciente (id, apellido_paciente, dni_paciente, nombre_paciente)
    VALUES
    (1, 'Gómez', 12345678, 'Ana'),
    (2, 'Martínez', 23456789, 'Juan'),
    (3, 'Rodríguez', 34567890, 'Laura'),
    (4, 'Fernández', 45678901, 'Diego'),
    (5, 'Pérez', 56789012, 'Elena'),
    (6, 'López', 67890123, 'Carlos'),
    (7, 'Díaz', 78901234, 'María'),
    (8, 'Sánchez', 89012345, 'Pedro'),
    (9, 'Ramírez', 90123456, 'Carmen'),
    (10, 'Gutiérrez', 10111213, 'Javier'),
    (11, 'Hernández', 11121314, 'Sofía'),
    (12, 'Torres', 12131415, 'Miguel'),
    (13, 'Núñez', 13141516, 'Luisa'),
    (14, 'Romero', 14151617, 'Antonio'),
    (15, 'Vargas', 15161718, 'Isabel'),
    (16, 'Cruz', 16171819, 'Francisco'),
    (17, 'Molina', 17181920, 'Ana María'),
    (18, 'Ortega', 18192021, 'Roberto'),
    (19, 'Fuentes', 19202122, 'Patricia'),
    (20, 'Reyes', 20212223, 'Daniel');
    
    -- Profesionales
    INSERT INTO basedatosclinica.profesional (id, nombre_profesional, apellido_profesional, horario_entrada, horario_salida, matricula)
    VALUES
    (1, 'Dr. Juan', 'Gómez', 8, 12, 123456),
    (2, 'Dra. María', 'Martínez', 12, 16, 234567),
    (3, 'Dr. Carlos', 'Rodríguez', 16, 20, 345678),
    (4, 'Dra. Laura', 'Fernández', 20, 23, 456789),
    (5, 'Dr. Pedro', 'Pérez', 8, 12, 567890),
    (6, 'Dra. Sofía', 'López', 12, 16, 678901),
    (7, 'Dr. Miguel', 'Díaz', 16, 20, 789012),
    (8, 'Dra. Ana', 'Sánchez', 20, 23, 890123),
    (9, 'Dr. Diego', 'Ramírez', 8, 12, 901234),
    (10, 'Dra. Elena', 'Gutiérrez', 12, 16, 812345),
    (11, 'Dra. Marta', 'Gonzaléz', 12, 16, 446145);
    
    -- Cargar especialidades a los profesionales
    INSERT INTO basedatosclinica.profesional_especialidades (profesional_id, especialidades_id)
    VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 2),
    (6, 2),
    (7, 2),
    (8, 2),
    (9, 3),
    (10, 3),
    (1, 3),
    (2, 3),
    (3, 4),
    (4, 4),
    (5, 4),
    (6, 4),
    (7, 1),
    (8, 1),
    (9, 2),
    (10, 2),
    (11, 5);
    
    -- Turnos
    INSERT INTO basedatosclinica.turno (id, fechay_hora_turno, consultorio_id, especialidad_id, paciente_id, profesional_id)
    VALUES
    (1, TIMESTAMP('2024-01-01 08:00:00'), 1, 1, 1, 1),
    (2, TIMESTAMP('2024-01-02 09:00:00'), 2, 1, 2, 2),
    (3, TIMESTAMP('2024-01-03 10:00:00'), 3, 1, 3, 3),
    (4, TIMESTAMP('2024-01-04 11:00:00'), 1, 1, 4, 4),
    (5, TIMESTAMP('2024-01-05 12:00:00'), 2, 2, 5, 5),
    (6, TIMESTAMP('2024-01-06 13:00:00'), 3, 2, 6, 6),
    (7, TIMESTAMP('2024-01-08 14:00:00'), 1, 2, 7, 7),
    (8, TIMESTAMP('2024-01-08 15:00:00'), 2, 2, 8, 8),
    (9, TIMESTAMP('2024-01-09 16:00:00'), 3, 3, 9, 9),
    (10, TIMESTAMP('2024-01-10 17:00:00'), 1, 3, 10, 10),
    (11, TIMESTAMP('2024-01-11 18:00:00'), 2, 3, 11, 1),
    (12, TIMESTAMP('2024-01-12 19:00:00'), 3, 3, 12, 2),
    (13, TIMESTAMP('2024-01-13 20:00:00'), 1, 4, 13, 3),
    (14, TIMESTAMP('2024-01-20 21:00:00'), 2, 4, 14, 4),
    (15, TIMESTAMP('2024-01-15 22:00:00'), 3, 4, 15, 5),
    (16, TIMESTAMP('2024-01-16 08:00:00'), 1, 4, 16, 6),
    (17, TIMESTAMP('2024-01-17 09:00:00'), 2, 1, 17, 7),
    (18, TIMESTAMP('2024-01-18 10:00:00'), 3, 1, 18, 8),
    (19, TIMESTAMP('2024-01-19 11:00:00'), 1, 2, 19, 9),
    (20, TIMESTAMP('2024-01-20 12:00:00'), 2, 2, 20, 10),
    (21, TIMESTAMP('2024-01-13 13:00:00'), 3, 1, 1, 1),
    (22, TIMESTAMP('2024-01-14 14:00:00'), 1, 1, 2, 2),
    (23, TIMESTAMP('2024-01-15 15:00:00'), 2, 1, 3, 3),
    (24, TIMESTAMP('2024-01-16 16:00:00'), 3, 1, 4, 4),
    (25, TIMESTAMP('2024-01-17 17:00:00'), 1, 2, 5, 5),
    (26, TIMESTAMP('2024-01-18 18:00:00'), 2, 2, 6, 6),
    (27, TIMESTAMP('2024-01-19 19:00:00'), 3, 2, 7, 7),
    (28, TIMESTAMP('2024-01-20 20:00:00'), 1, 2, 8, 8),
    (29, TIMESTAMP('2024-01-18 18:00:00'), 1, 3, 9, 9),
    (30, TIMESTAMP('2024-01-22 22:00:00'), 3, 3, 10, 10),
    (31, TIMESTAMP('2024-01-23 08:00:00'), 1, 3, 11, 1),
    (32, TIMESTAMP('2024-01-24 09:00:00'), 2, 3, 12, 2),
    (33, TIMESTAMP('2024-01-25 10:00:00'), 3, 4, 13, 3),
    (34, TIMESTAMP('2024-01-26 11:00:00'), 1, 4, 14, 4),
    (35, TIMESTAMP('2024-01-27 12:00:00'), 2, 4, 15, 5),
    (36, TIMESTAMP('2024-01-25 13:00:00'), 3, 4, 16, 6),
    (37, TIMESTAMP('2024-01-29 14:00:00'), 1, 1, 17, 7),
    (38, TIMESTAMP('2024-01-30 15:00:00'), 2, 1, 18, 8),
    (39, TIMESTAMP('2024-01-31 16:00:00'), 3, 2, 19, 9),
    (40, TIMESTAMP('2024-02-01 17:00:00'), 1, 2, 20, 10);
```