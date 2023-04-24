# Aplicación de Taller de Carros en Java

Esta es una aplicación de escritorio en Java que permite gestionar los servicios prestados por un taller de carros. La aplicación utiliza una base de datos MySQL para almacenar la información de los vehículos, sus propietarios y los servicios realizados. 

## Características de la Aplicación

La aplicación tiene las siguientes características:

- Gestión de vehículos: Permite registrar vehículos con información como placa, tipo de vehículo (automóvil, camioneta, etc.), estado (bueno, regular, malo), motivo de ingreso al taller (mecánica en general, cambio de aceite, alineación y rotación de ruedas, etc.), propietario (cedula, nombres y apellidos, carta de propiedad del vehículo, dirección, teléfono, etc.), fecha de ingreso y fecha de entrega.
- Gestión de servicios: Permite registrar los servicios realizados a los vehículos, incluyendo el costo del servicio en términos de mano de obra y repuestos.
- Restricciones de vehículos: La aplicación no permite registrar vehículos de tráfico pesado como tractomulas, doble troques o camiones, ni maquinaria pesada como cargadores o retroexcavadoras.

## Requerimientos del Usuario

La aplicación busca optimizar los servicios prestados por el taller, asegurando la comodidad y satisfacción del usuario, así como de los empleados frente a un trabajo a satisfacción. Además, se espera que los servicios sean realizados en un tiempo acorde a los requerimientos del usuario. Por ejemplo, si el servicio es una alineación, el vehículo debe demorarse en el taller un máximo de 6 horas, dependiendo de los turnos de los asistentes; de lo contrario, no se debe hacer la entrada del vehículo.

## Tecnologías Utilizadas

La aplicación utiliza las siguientes tecnologías:

- Java: Lenguaje de programación utilizado para desarrollar la aplicación.
- MySQL: Sistema de gestión de bases de datos utilizado para almacenar la información de los vehículos, propietarios y servicios.

## Instalación y Configuración

Para utilizar la aplicación, sigue estos pasos:

1. Clona o descarga el repositorio de la aplicación en tu máquina local.
2. Crea una base de datos MySQL mediante el script proporcionado.
3. Importa el archivo de script SQL proporcionado en la carpeta `db` del repositorio en la base de datos MySQL para crear las tablas necesarias.
4. Cambia la configuracion de la base de datos en la clase conexion con los datos de tu base de datos
5. Compila y ejecuta la aplicación en tu entorno de desarrollo Java.
