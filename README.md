# Online Shop Backend

## Overview

This project is a **backend system for an online shop** that provides a robust and secure platform for managing users, products, and orders. It leverages **Spring Boot**, **Spring Security**, and other key technologies to deliver seamless e-commerce functionality, including authentication, product management, shopping cart operations, and payment integration using Stripe.

## Features

### User Authentication & Authorization
- **User Registration and Login** – Secure login using JWT (JSON Web Token).
- **Role-Based Authentication** – Admin and User roles to control access to specific features.

### Product Management
- Add, update, fetch, and delete products.
- Organized product data using DTOs (Data Transfer Objects) and Model Mapper for clean object transformation.

### Shopping Cart Operations
- Add items to the cart.
- Update cart items, including quantity.
- Remove items from the cart.

### Checkout & Payment
- **Stripe Payment Integration** – Securely process payments through Stripe APIs.

## Technologies Used

- **Spring Boot** – Provides the backbone for this backend API.
- **Spring Security** – For role-based authentication and secure endpoints.
- **JWT (JSON Web Tokens)** – For stateless authentication.
- **Spring Data JPA** – Simplifies database operations via ORM.
- **MySQL** – Persistent relational database for storing application data.
- **ModelMapper** – For object mapping between entities and DTOs.
- **Stripe** – For processing payments effectively.

## API Functionality

| Feature                  | Description                                |
|--------------------------|--------------------------------------------|
| User Authentication      | Register, log in, and access specific resources using JWT. |
| Product Management       | Add, retrieve, update, and delete products as an Admin. |
| Cart Management          | Add, update, delete items, and manage cart quantities. |
| Payment                 | Process payments securely with Stripe.    |

## Installation & Usage

### Prerequisites
- **Java SDK 21** or later installed.
- **Maven** (or another compatible build tool).
- A running **MySQL** instance with database configured.
- Stripe Account & API keys (test keys for development).

### Setup Steps
1. **Clone the repository**:
   ```bash
   git clone <repository_url>
   cd <project_directory>
   ```

2. **Update the application properties:**
    - Add your **MySQL credentials**.
    - Add your **Stripe API keys**.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
   spring.datasource.username=<db_username>
   spring.datasource.password=<db_password>

   stripe.api-key=<your_stripe_api_key>
   ```

3. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application:**
    - Default **Base URL**: `http://localhost:8080`
    - Test APIs using Postman, curl, or other API clients.

## Contributions

Contributions to enhance features or improve documentation are welcome. Follow these steps to contribute:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m "Add new feature"`.
4. Push the changes: `git push origin feature-name`.
5. Open a pull request for review.

---

Thank you for exploring the **Online Shop Backend**! If you have any feedback or ideas, feel free to reach out.