# Finance Data Processing and Access Control Backend

## Overview

This project is a backend system for a finance dashboard application. It provides APIs for managing users, financial transactions, and dashboard analytics. The system includes role-based access control, authentication using JWT, and data persistence using MySQL.

The purpose of this project is to demonstrate backend architecture, API design, data modeling, business logic implementation, and secure access control.

---

# Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA / Hibernate
* MySQL
* Maven

---

# Key Features

## 1. User and Role Management

The system supports multiple user roles with different permissions.

Roles implemented:

* **Admin** – Full access to manage users and financial records
* **Analyst** – Can view records and dashboard insights
* **Viewer** – Can only view dashboard data

Features:

* User registration
* User login
* Role assignment
* User activation / deactivation
* JWT-based authentication

---

# 2. Financial Records Management

The backend supports CRUD operations for financial transactions.

Each transaction includes:

* Amount
* Type (Income / Expense)
* Category
* Date
* Description
* Associated User

Supported operations:

* Create transaction
* Retrieve transactions
* Update transaction
* Delete transaction
* Filter transactions by:

  * Type
  * Category
  * Date range

---

# 3. Dashboard Summary APIs

The backend provides aggregated data for a financial dashboard.

Available analytics:

* Total income
* Total expenses
* Net balance
* Category-wise totals
* Monthly financial trends
* Recent transaction activity

These APIs demonstrate how backend services can process and aggregate financial data for dashboard visualization.

---

# 4. Authentication and Security

Security is implemented using **Spring Security and JWT tokens**.

Features include:

* Secure login
* Token-based authentication
* Protected API endpoints
* Role-based access control

Only authenticated users can access protected resources.

---

# 5. Validation and Error Handling

The application includes validation for input data using annotations such as:

* `@NotBlank`
* `@NotNull`
* `@Positive`

The system also handles common error scenarios such as:

* Invalid input
* User not found
* Transaction not found
* Unauthorized access

---

# Database

The application uses **MySQL** as the primary database.

Entities used:

* **User**
* **Transaction**

Hibernate and Spring Data JPA are used for ORM and database interaction.

---

# Project Structure

```
finance-backend
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 ├── security
 └── FinanceBackendApplication
```

This structure follows a layered architecture separating controllers, services, and persistence logic.

---

# API Endpoints

## Authentication

POST /api/auth/register
POST /api/auth/login

---

## Transactions

POST /api/transactions
GET /api/transactions
PUT /api/transactions/{id}
DELETE /api/transactions/{id}

Filtering supported using query parameters.

---

## Dashboard

GET /api/dashboard/summary
GET /api/dashboard/categories
GET /api/dashboard/trends
GET /api/dashboard/recent

---

# Running the Project

### 1. Clone the repository

```
git clone <repository-url>
```

### 2. Configure database

Update `application.properties` with MySQL credentials.

### 3. Run the application

```
mvn spring-boot:run
```

Server will start on:

```
http://localhost:8080
```

---

# Assumptions

* JWT is used for authentication.
* Role-based access is implemented using Spring Security.
* MySQL is used for persistent storage.
* APIs are designed to support a frontend finance dashboard.

---

# Conclusion

This backend system demonstrates a structured approach to building secure and maintainable APIs for a finance management system. It highlights core backend engineering concepts including authentication, role-based access control, database design, and API architecture.
