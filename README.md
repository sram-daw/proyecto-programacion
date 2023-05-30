# Proyecto tercer trimestre: aplicación de tienda

## Visión general
El objetivo del proyecto es desarrollar una aplicación de tienda con un inventario asociado. Podrán acceder a ella dos tipos de usuarios: administradores y clientes. Los clientes podrán efectuar compras seleccionando productos y cantidades, que se restarán del cómputo total del stock. Además, cada cliente podrá acceder al historial de todos sus pedidos. Por otra parte, los administradores podrán reponer y comprobar stock, visualizar los clientes dados de alta con todos sus datos y el historial total de pedidos, pudiendo comprobar qué cliente realizó cada uno y el contenido del mismo.


>Nota: El archivo SQL de la base de datos se encuentra en la carpeta resources. El conector JDBC es el de MySQL y también está en resources.
>IMPORTANTE: introducir el siguiente comando en la bd antes de ejecutar el programa: SET GLOBAL time_zone = '+02:00';

>En la carpeta resources>libs se encuentran las librerías necesarias para que funcione itextpdf.
>El archivo log4j.properties desactiva warnings en la creación de las tablas del pdf.
