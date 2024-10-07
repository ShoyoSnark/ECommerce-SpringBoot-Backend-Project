# API REST para una Aplicación de Comercio Electrónico

## Tecnología utilizada
* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Módulos 
* Módulo de Inicio y Cierre de Sesión
* Módulo de Vendedores
* Módulo de Clientes
* Módulo de Productos
* Módulo de Carrito
* Módulo de Pedidos
## Características
* Autenticación y validación de Clientes y Vendedores con un token de sesión válido por 1 hora para fines de seguridad.
* Funciones del Vendedor:
   * Rol de Administrador de toda la aplicación.
   * Solo el vendedor registrado con un token de sesión válido puede añadir/actualizar/eliminar productos de la base de datos principal.
   * El vendedor puede acceder a los detalles de diferentes clientes y pedidos.
* Funciones del Cliente:
   * Registrarse en la aplicación e iniciar sesión para obtener un token de sesión válido.
   * Ver diferentes productos, añadirlos al carrito y realizar pedidos.
   * Solo el usuario que haya iniciado sesión puede acceder a sus pedidos, carrito y otras funciones.


## Instalación y Ejecución
* Primero, deben contar con las siguientes herramientas
  * Visual Studio Code
  * Java 8
  * MySQL 
### Preparación Visual Studio Code 
* Una vez instalado Visual Studio, deben instalar las siguientes extensiones:
  * Extension Pack for Java.
  * Spring Initializr Java Support.
### Preparación MySQL
* Una vez instalado MySQL, deben crear una base de datos con el nombre "ecommercedb"
* Luego de crear la base de datos, el servidor API rellenara de forma automatica las tablas de la base de datos.


* Antes de ejecutar el servidor API, debes actualizar la configuración de la base de datos dentro del archivo application.properties(E-Commerce-Backend\src\main\resources\application.properties).
* Actualiza el número de puerto, el nombre de usuario y la contraseña según la configuración de tu base de datos local.

```
    server.port=8009(Puerto en el que se inicializa el servidor"

    spring.datasource.username=root(Nombre de usuario base de datos)
    spring.datasource.password=root(Contraseña base de datos)

```

### Ejecución del servidor.

* Para ejecutar la API, desde Visual Studio Code deben abrir el archivo deECommerceBackendApplication(E-Commerce-Backend\src\main\java\com\masai\ECommerceBackendApplication.java)
* Una vez abierto el archivo, encima de la función public static void main. Les saldra dos opciones, Run|Debug, seleccionan Run y esperan que se inicialice el servidor API

## Endpoint Raíz de la API
* Para acceder a la API desde el navegador, ingresen la siguiente URL:
  * `http://localhost:8009/swagger-ui/index.html#/`

## Endpoints de los Módulos de la API

### Módulo de Inicio y Cierre de Sesión
* `POST /register/customer` : Registrar un nuevo cliente.
* `POST /login/customer` : Iniciar sesión para un cliente con número de móvil y contraseña válidos.
* `POST /logout/customer` : Cerrar sesión del cliente basado en el token de sesión.
* `POST /register/seller` : Registrar un nuevo vendedor.
* `POST /login/seller` : Iniciar sesión para el vendedor.
* `POST /logout/seller` : Cerrar sesión del vendedor basado en el token de sesión.


### Módulo de Clientes
* `GET /customer/current` : Obtener el cliente actualmente registrado.
* `GET /customer/orders` : Obtener el historial de pedidos del cliente registrado.
* `GET /customers` : Obtener todos los clientes.
* `PUT /customer` : Actualizar al cliente registrado.
* `PUT /customer/update/password` : Actualizar la contraseña del cliente.
* `PUT /customer/update/card` : Actualizar los detalles de la tarjeta de crédito.
* `PUT /customer/update/address?type=home` : Actualizar la dirección de casa del cliente.
* `PUT /customer/update/credentials` : Actualizar el correo electrónico y el número de móvil.
* `DELETE /customer` : Eliminar al usuario registrado con un token de sesión válido.
* `DELETE /customer/delete/address?type=home` : Eliminar la dirección de casa del cliente.


### Módulo de Vendedores

* `GET /seller/{sellerid}` : Obtener el vendedor con el ID proporcionado.
* `GET /seller/current` : Obtener los detalles del vendedor actualmente registrado.
* `GET /sellers` : Obtener todos los vendedores.
* `POST /addseller` : Añadir un nuevo vendedor.
* `PUT /seller` : Actualizar los detalles del vendedor.
* `PUT /seller/update/password` : Actualizar la contraseña del vendedor.
* `PUT /seller/update/mobile` : Actualizar el número de móvil del vendedor.
* `DELETE /seller/{sellerid}` : Eliminar el vendedor con el ID proporcionado.


### Módulo de Productos

* `GET /product/{id}` : Obtener el producto con el ID proporcionado.
* `GET /products` : Obtener todos los productos.
* `GET /products/{category}` : Obtener productos por categoría.
* `GET /products/seller/{id}` : Obtener productos del vendedor con el ID proporcionado.
* `POST /products` : Añadir un nuevo producto a la base de datos.
* `PUT /products` : Actualizar el producto con el ID proporcionado.
* `PUT /products/{id}` : Actualizar la cantidad del producto.
* `DELETE /product/{id}` : Eliminar el producto con el ID proporcionado.


### Módulo de Carrito

* `GET /cart` : Obtener todos los artículos en el carrito del cliente.
* `POST /cart/add` : Añadir un artículo al carrito.
* `DELETE /cart` : Eliminar un artículo del carrito.
* `DELETE /cart/clear` : Vaciar todo el carrito.


### Módulo de Pedidos
* `GET /orders/{id}` : Obtener los detalles del pedido con el ID proporcionado.
* `GET /orders` : Obtener todos los pedidos.
* `GET /orders/by/date` : Obtener los pedidos realizados en la fecha proporcionada (DD-MM-AAAA).
* `POST /order/place` : Realizar un nuevo pedido basado en los artículos del carrito.
* `PUT /orders/{id}` : Actualizar un pedido pendiente.
* `DELETE /orders/{id}` : Cancelar un pedido.


### Ejemplo de Respuesta API para el Inicio de Sesión del Cliente

`POST   localhost:8009/login/customer`

* Request Body

```
    {
        "mobileId": "9999999999",
        "password": "shyam123456"
    }
```

* Response

```
    {
        "sessionId": 23,
        "token": "customer_0ad57094",
        "userId": 19,
        "userType": "customer",
        "sessionStartTime": "2022-06-10T10:48:20.0109626",
        "sessionEndTime": "2022-06-10T11:48:20.0109626"
    }
```
