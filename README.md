# Malackathon

Idea:
La idea principal es que introduciendo las coordenadas, ya sea de forma manual o a través de la ubicación del dispositivo, muestre en un mapa los diferentes embalses dentro del rango establecido con diferentes marcas de las ubicaciones de los embalses. Al hacer click en el embalse, de mostrará los datos correspondientes a ese embalse, los cuales estáran antes todos en una tabla ordenados en orden alfabético. Además, existirán diferentes filtrado en una columna de la tabla junto con un checkbox, para hacer click en el filtrado deseado.

Base de datos:
En la base de datos, se ha creado una tabla intermedia llamada EMBALSES_RELACIONADOS, donde se relaciona la tabla EMBALSES a través del id y el nombre del embalse junto con la tabla LISTADO a través del codigo y el nombre del embalse, para ello se hace un join de ambas tablas y se extrae la información correspondiente.

Frontend:
Para el frontend se usará React junto con Bootstrap, se utilizará axios para las diferentes llamadas al backend.

Backend:
Para el backend se usa Spring Boot, usando el modelo vista controlador, donde se hace las usa las llamadas a la api rest, para controlar las llamadas, se limitará las tasas de la misma evitando así ataques DoS, además se añadirá CAPTCHA cada vez que un usuario quiera buscar los embalses, para validar los diferentes usuario y las solicitudes
