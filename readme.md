# E-Commerce API

Este proyecto implementa una API RESTful para una plataforma de comercio electrónico con funcionalidades para gestión de usuarios, productos y carrito de compras.

## Características principales

- **Gestión de usuarios**: registro e inicio de sesión con autenticación JWT
- **Roles de usuario**: compradores y vendedores con diferentes permisos
- **Gestión de productos**: crear, editar, eliminar y listar productos
- **Carrito de compras**: agregar, actualizar, eliminar y finalizar compra
- **Control de inventario**: validación automática de stock disponible

## Tecnologías utilizadas

- Spring Boot
- Spring Security + JWT
- JPA / Hibernate
- MySQL

## Estructura del proyecto

El proyecto sigue una arquitectura en capas:

- **Controllers**: manejo de las peticiones HTTP
- **Services**: lógica de negocio
- **Repositories**: acceso a datos
- **Entities**: mapeo objeto-relacional

## Configuración de seguridad

La aplicación utiliza tokens JWT para autenticación y autorización:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                    .requestMatchers("/user/register", "/user/authenticate").permitAll()
                    .requestMatchers(HttpMethod.POST, "/products").hasAuthority(Role.VENDEDOR.name())
                    .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAuthority(Role.VENDEDOR.name())
                    .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAuthority(Role.VENDEDOR.name())
                    .requestMatchers("/cart/**").hasAuthority(Role.COMPRADOR.name())
                    .requestMatchers(HttpMethod.GET, "/products/**").authenticated()
                    .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
```

## Endpoints principales

### Autenticación

- `POST /user/register`: Registro de nuevo usuario
- `POST /user/authenticate`: Inicio de sesión y obtención de token JWT

### Productos

- `GET /products`: Obtener todos los productos
- `GET /products/{id}`: Obtener producto por ID
- `GET /products/category/{id}`: Obtener productos por categoría
- `POST /products`: Crear nuevo producto (solo vendedores)
- `PUT /products/{id}`: Actualizar producto (solo vendedores)
- `DELETE /products/{id}`: Eliminar producto (solo vendedores)

### Carrito de compras

- `GET /cart`: Obtener carrito del usuario
- `POST /cart/items`: Agregar producto al carrito
- `PUT /cart/items/{itemId}`: Actualizar cantidad de un producto
- `DELETE /cart/items/{itemId}`: Eliminar producto del carrito
- `POST /cart/checkout`: Finalizar compra

### Category

- `GET /catetory`: Obtener todos los categories
- `GET /catetory/{id}`: Obtener category por ID
- `POST /catetory`: Crear nuevo category (solo admins)
- `PUT /catetory/{id}`: Actualizar category (solo admins)
- `DELETE /catetory/{id}`: Eliminar category (solo admins)

## Ejemplos de uso con cURL

### Autenticación y obtención de token

```bash
curl --location 'http://localhost:8080/user/authenticate' \
--header 'Content-Type: application/json' \
--data '{
    "username": "usuario@example.com",
    "password": "password123"
}'
```

### Agregar producto al carrito

```bash
curl --location 'http://localhost:8080/cart/items' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN' \
--data '{
    "productId": 1,
    "quantity": 2
}'
```

### Realizar checkout

```bash
curl --location 'http://localhost:8080/cart/checkout' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN'
```

## Configuración de la aplicación

La aplicación utiliza las siguientes propiedades de configuración:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

# Imágenes
upload.image.path=uploads/images/products/
image.access.path=/images/products/
spring.web.resources.static-locations=file:uploads/
```

## Ejecución del proyecto

1. Clonar el repositorio
2. Configurar la base de datos MySQL
3. Ajustar las propiedades en `application.properties`
4. Ejecutar con Maven: `mvn spring-boot:run`
