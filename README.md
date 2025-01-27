# Uber-Like Ride Booking Application

This project is a **scalable ride-booking application** similar to Uber, built using **Spring Boot**. It supports **real-time ride requests, driver matching, payment processing, and authentication**. The system is designed for high availability, security, and performance.

## 🚀 Key Highlights
- **Optimized Geospatial Queries**: Utilizes **PostGIS** in PostgreSQL to calculate distances between riders and drivers efficiently, enhancing the ride-matching process.
- **Secure Authentication**: Implements **Spring Security with JWT** for robust **user authentication and role-based authorization**, ensuring data security.
- **Scalable Microservices Architecture**: Designed for **high availability** with separate services for user management, ride requests, payments, and driver management.
- **Cloud Deployment**: Deployed on **AWS** using **RDS, Elastic Beanstalk, and EC2**, with **automated CI/CD pipelines through AWS CodePipeline**.
- **Robust Testing**: Achieves **high test coverage** with **JUnit and Mockito**, ensuring reliability across critical system features.

## Table of Contents

- [Technologies](#technologies)
- [Architecture](#architecture)
- [Diagrams](#diagrams)
- [Services](#services)
- [API Endpoints](#api-endpoints)
- [Setup](#setup)
- [Usage](#usage)
- [Exception Handling](#exception-handling)
- [Contributing](#contributing)
- [License](#license)

## Technologies

- Java
- Spring Boot
- Spring Cloud
- Maven
- PostgreSQL with PostGIS
- Spring Security & JWT
- AWS (RDS, Elastic Beanstalk, EC2, CodePipeline)
- JUnit & Mockito
- Swagger

## Architecture

The application follows a **microservices architecture** with the following services:

- **User Service**: Manages authentication, authorization, and wallet.
- **Rider Service**: Handles ride requests and cancellations.
- **Driver Service**: Manages ride acceptance and completion.
- **Ride Request Service**: Matches riders with drivers using various strategies.
- **Payment Service**: Processes payments and manages user wallets.

## Diagrams

**Design Flow Diagram:**

<img width="1208" alt="Screenshot 2025-01-25 at 12 33 05 PM" src="https://github.com/user-attachments/assets/012825fc-3ea2-4a4b-ac61-9626179136fc" />


**UML Diagram:**

<img width="1208" alt="Screenshot 2025-01-25 at 12 33 28 PM" src="https://github.com/user-attachments/assets/3124d03d-8287-47f0-ba13-615f025b51d5" />


## Services

### User Service
Manages user authentication, authorization, and wallet transactions.

### Rider Service
Handles ride requests, cancellations, and ratings for drivers.

### Driver Service
Manages ride acceptance, completion, and driver ratings.

### Ride Request Service
Handles driver matching strategies and ride assignments.

### Payment Service
Manages payment transactions and wallet balances.

## API Endpoints

### User Service
- `POST /user/signup` - Register a new user
- `POST /user/login` - Authenticate user
- `POST /user/logout` - Logout user

### Rider Service
- `GET /rider/getAllRides` - Get all available rides
- `POST /rider/requestRide` - Request a ride
- `POST /rider/cancelRide` - Cancel a ride
- `POST /rider/rateDriver` - Rate a driver
- `POST /rider/addFunds` - Add funds to wallet

### Driver Service
- `GET /driver/getAllRides` - Get all ride requests
- `POST /driver/acceptRide` - Accept a ride
- `POST /driver/startRide` - Start a ride
- `POST /driver/endRide` - End a ride
- `POST /driver/cancelRide` - Cancel a ride
- `POST /driver/rateRider` - Rate a rider
- `POST /driver/debitFunds` - Debit funds from wallet

### Ride Request Service
- `POST /ride/request` - Request a ride
- `POST /ride/cancel` - Cancel a ride
- `POST /ride/accept` - Accept a ride

### Payment Service
- `POST /payment/charge` - Process a payment
- `POST /wallet/addFunds` - Add funds to wallet
- `POST /wallet/deductFunds` - Deduct funds from wallet


## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/uber-app.git
   cd uber-app
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

3. Run the services:
   ```sh
   cd user-service
   mvn spring-boot:run
   
   cd ../rider-service
   mvn spring-boot:run
   
   cd ../driver-service
   mvn spring-boot:run
   
   cd ../ride-request-service
   mvn spring-boot:run
   
   cd ../payment-service
   mvn spring-boot:run
   ```

## Usage

The services can be accessed via their respective endpoints. Use tools like Postman or cURL to interact with the APIs.

## Exception Handling

Global exception handling is implemented using `@RestControllerAdvice` and custom exception classes.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request.

## License

This project is licensed under the MIT License.

---
🚀 **Ready to contribute?** Fork the repository and submit a PR!
