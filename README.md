# BookStore Management System

This is a RESTful API built with **Spring Boot 3.5.6** and **Java 21** for managing a Bookstore. It features full security setup using Spring Security 6, JPA for persistence with MySQL, and Project Lombok for reducing boilerplate.

-----

## 🚀 Key Technologies

  * **Framework:** Spring Boot 3.5.6
  * **Language:** Java 21 (LTS)
  * **Persistence:** Spring Data JPA / Hibernate 6
  * **Database:** MySQL
  * **Security:** Spring Security 6
  * **Tooling:** Maven, Project Lombok (1.18.32+ is required for Java 21)
  * **API Documentation:** Springdoc OpenAPI (Swagger UI)

-----

## 🛠️ Project Setup & Installation

### Prerequisites

  * JDK 21
  * Maven 3.6+
  * MySQL Server running on `localhost:3306`

### 1\. Database Configuration

Ensure your MySQL database configuration in `src/main/resources/application.properties` is correct.

| Property | Value | Notes |
| :--- | :--- | :--- |
| `spring.datasource.url` | `jdbc:mysql://localhost:3306/bookstore` | The database **`bookstore`** must exist. |
| `spring.datasource.username` | `root` | Replace with your actual database user. |
| `spring.datasource.password` | `vISHNU#08` | **CRITICAL:** Replace with your actual password. |
| `spring.jpa.properties.hibernate.dialect` | `org.hibernate.dialect.MySQLDialect` | **FIXED:** Correct dialect for Hibernate 6. |
| `spring.jpa.hibernate.ddl-auto` | `update` | Automatically creates/updates tables. |

**Important Note on Connection:**
If you encounter the **"Access Denied"** error (`Access denied for user 'root'@'localhost'`), you must verify your password is correct, or update the `root` user's authentication plugin in MySQL to be compatible with the Java connector (e.g., using `mysql_native_password`).

### 2\. Run the Application

Navigate to the project root directory and run:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

-----

## ✅ Mandatory Compatibility Fixes (Resolved)

The transition to Java 21 and Spring Boot 3.5.6 required several package and dependency updates:

| Category | Old (Removed) | New (Required) |
| :--- | :--- | :--- |
| **Persistence (JPA)** | `javax.persistence.*` | **`jakarta.persistence.*`** |
| **Validation** | `javax.validation.constraints.*` | **`jakarta.validation.constraints.*`** |
| **Spring Security** | `WebSecurityConfigurerAdapter` | **`SecurityFilterChain`** bean structure |
| **Lombok** | Old versions (e.g., 1.18.24) | **`1.18.32`** or higher (required for JDK 21) |

-----

## 🔒 Security & Authentication (Spring Security 6)

The security layer is configured to use **Form Login** and **HTTP Basic Authentication**, secured by your custom `UserDetailsService` and `BCryptPasswordEncoder`.

### Key Security Implementations

  * **Modern Configuration:** Uses the **`@Bean SecurityFilterChain`** and **`@EnableMethodSecurity`** (replacing deprecated annotations/classes).
  * **Password Encoding Fix:** The `MyUserDetailsService` now correctly uses the injected **`BCryptPasswordEncoder`** to hash all passwords before saving new users. **This was the fix for sign-in failures.**
  * **Authentication Flow:** The application relies on the `authProvider` bean to delegate authentication to the `UserDetailsService`, ensuring proper password verification against the BCrypt hashes.

### Access Control Examples

| Resource | Method | Role Required |
| :--- | :--- | :--- |
| `/signup`, `/login` | `POST` | `permitAll()` |
| `/books/**`, `/category/**` | `GET` | `permitAll()` |
| `/books/**`, `/publisher/**` | `POST`, `PUT`, `DELETE` | `Admin` |
| `/orderItems/**`, `/feedback/**` | All | `User` or `Admin` |
| `/customer/{id}` | `PUT` | `User` |
