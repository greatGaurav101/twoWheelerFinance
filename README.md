# Two Wheeler Finance

A microservices-based application for managing financing of two-wheeler vehicles. This project is built using Spring Boot and Spring Cloud architecture to handle user management, vehicle management, lending operations, and notification services with event-driven messaging.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Services](#services)
- [Messaging & Events](#messaging--events)
- [Running the Services](#running-the-services)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Project Overview

Two Wheeler Finance is a distributed application designed to streamline the finance management process for two-wheeler vehicles. It leverages microservices architecture with service discovery, inter-service communication, and event-driven messaging to provide a robust and scalable solution for managing users, vehicles, lending operations, and notifications.

## 🏗️ Architecture

This project follows a **microservices architecture** with the following components:

- **Service Discovery**: Netflix Eureka for dynamic service registration and discovery
- **Inter-Service Communication**: OpenFeign for declarative REST clients
- **Event-Driven Messaging**: Apache Kafka for asynchronous communication between services
- **Individual Services**: Each service manages its own database (database per service pattern)
- **Notifications**: Multi-channel notification service (Email, SMS via Twilio)

### High-Level Architecture Diagram

```
┌──────────────────────────────────────────────────────────┐
│                  Client Applications                     │
└──────────────────┬───────────────────────────────────────┘
                   │
        ┌──────────┼──────────┐
        │          │          │
    ┌───▼────────┐ │  ┌──────▼──────┐   ┌───────────┐
    │ User       │ │  │ Vehicle     │   │ Lender    │
    │ Service    │ │  │ Service     │   │ Service   │
    └───┬────────┘ │  └──────┬──────┘   └─────┬─────┘
        │          │         │                 │
        └──────────┼─────────┼─────────────────┘
                   │         │
                   └────┬────┘
                        │ (Kafka Events)
                        │
            ┌───────────▼──────────────┐
            │   Notification Service   │
            │  (Email, SMS, In-App)    │
            └──────────────────────────┘
```

## 📦 Prerequisites

- **Java 17** or higher
- **Maven 3.8.0** or higher
- **MySQL 8.0** or higher
- **Apache Kafka 3.x** or higher
- **Redis** (optional, for caching in Vehicle Service)
- **Git**

## 🛠️ Technology Stack

| Component | Technology |
|-----------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.13+ |
| Cloud Framework | Spring Cloud 2025.0.2 |
| Build Tool | Maven |
| Database | MySQL 8.0 |
| ORM | JPA/Hibernate |
| Service Discovery | Netflix Eureka |
| Inter-Service Communication | OpenFeign |
| Message Broker | Apache Kafka |
| Caching | Redis (optional) |
| Email/SMS | Twilio SDK 10.1.0 |
| Dependency Injection | Lombok |
| Database Migration | Flyway (optional) |

## 📁 Project Structure

```
twoWheelerFinance/
├── user-service/                  # User management microservice
│   ├── src/
│   │   ├── main/java/com/rupyy/
│   │   └── test/java/
│   └── pom.xml
├── vehicle-service/               # Vehicle management microservice
│   ├── src/
│   │   ├── main/java/com/rupyy/
│   │   └── test/java/
│   └── pom.xml
├── lender-service/                # Lending operations microservice
│   ├── src/
│   │   ├── main/java/com/rupyy/
│   │   └── test/java/
│   └── pom.xml
├── notification-service/          # Multi-channel notification service
│   ├── src/
│   │   ├── main/java/com/rupyy/
│   │   └── test/java/
│   └── pom.xml
├── customer-events/               # Shared event models library
│   ├── src/
│   │   ├── main/java/com/rupyy/
│   │   └── test/java/
│   └── pom.xml
├── config-server/                 # Centralized configuration (optional)
│   └── pom.xml
├── service-registry/              # Eureka service registry
│   └── pom.xml
├── README.md
└── .gitignore
```

## ⚙️ Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/greatGaurav101/twoWheelerFinance.git
cd twoWheelerFinance
```

### 2. Kafka Setup

Download and setup Apache Kafka:

```bash
# Extract Kafka
tar -xzf kafka_2.13-3.x.x.tgz
cd kafka_2.13-3.x.x

# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# In another terminal, start Kafka broker
bin/kafka-server-start.sh config/server.properties
```

### 3. MySQL Setup

Create databases for each service:

```sql
CREATE DATABASE user_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE vehicle_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE lender_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE notification_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 4. Create Kafka Topics

```bash
# Create topics for event streaming
bin/kafka-topics.sh --create --topic user-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
bin/kafka-topics.sh --create --topic vehicle-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
bin/kafka-topics.sh --create --topic loan-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

### 5. Configure Application Properties

Update `application.properties` or `application.yml` in each service:

**User Service Example:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_service_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=user-service-group
```

**Notification Service (Email/SMS):**
```properties
twilio.account-sid=your_twilio_account_sid
twilio.auth-token=your_twilio_auth_token
twilio.phone-number=your_twilio_phone_number
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

### 6. Build All Services

```bash
mvn clean install
```

## 🚀 Running the Services

### Order of Execution

1. **Service Registry** (Eureka Server)
2. **Config Server** (if applicable)
3. **Core Services** (User, Vehicle, Lender)
4. **Notification Service**

### Run Individual Services

**Service Registry:**
```bash
cd service-registry
mvn spring-boot:run
```

**User Service:**
```bash
cd user-service
mvn spring-boot:run
# Runs on http://localhost:8081
```

**Vehicle Service:**
```bash
cd vehicle-service
mvn spring-boot:run
# Runs on http://localhost:8082
```

**Lender Service:**
```bash
cd lender-service
mvn spring-boot:run
# Runs on http://localhost:8083
```

**Notification Service:**
```bash
cd notification-service
mvn spring-boot:run
# Runs on http://localhost:8084
```

### Or Build and Run with JAR

```bash
mvn clean package
java -jar user-service/target/user-service-0.0.1-SNAPSHOT.jar
java -jar vehicle-service/target/vehicle-service-0.0.1-SNAPSHOT.jar
java -jar lender-service/target/lender-service-0.0.1-SNAPSHOT.jar
java -jar notification-service/target/notification-service-0.0.1-SNAPSHOT.jar
```

## 🔧 Services

### User Service
Manages user accounts, authentication, and profile information.
- **Port**: 8081 (default)
- **Database**: user_service_db
- **Key Features**:
  - User registration and authentication
  - Profile management
  - Publishes: User creation, update, and deletion events to Kafka
- **Key Endpoints**: 
  - `POST /users` - Create new user
  - `GET /users/{id}` - Get user details
  - `PUT /users/{id}` - Update user profile
  - `DELETE /users/{id}` - Delete user account

### Vehicle Service
Handles vehicle inventory, specifications, and financing options.
- **Port**: 8082 (default)
- **Database**: vehicle_service_db
- **Caching**: Redis (optional for performance)
- **Key Features**:
  - Vehicle catalog management
  - Specifications and details
  - Publishes: Vehicle creation and update events to Kafka
- **Key Endpoints**:
  - `POST /vehicles` - Add new vehicle
  - `GET /vehicles/{id}` - Get vehicle details
  - `PUT /vehicles/{id}` - Update vehicle
  - `GET /vehicles/search` - Search vehicles

### Lender Service
Manages lending operations, loan approvals, and interest calculations.
- **Port**: 8083 (default)
- **Database**: lender_service_db
- **Dependencies**: Consumes `customer-events` library for event models
- **Key Features**:
  - Loan application management
  - Interest calculation
  - Loan status tracking
  - Publishes: Loan events to Kafka
- **Key Endpoints**:
  - `POST /loans` - Create loan application
  - `GET /loans/{id}` - Get loan details
  - `PUT /loans/{id}/approve` - Approve loan
  - `PUT /loans/{id}/reject` - Reject loan

### Notification Service
Multi-channel notification system for user communications.
- **Port**: 8084 (default)
- **Database**: notification_service_db
- **Key Features**:
  - Email notifications (SMTP)
  - SMS notifications (Twilio integration)
  - In-app notifications
  - Event-driven triggered notifications
  - Notification history tracking
- **Supported Channels**:
  - Email (SMTP)
  - SMS (Twilio)
  - In-app notifications
- **Key Endpoints**:
  - `POST /notifications/email` - Send email
  - `POST /notifications/sms` - Send SMS
  - `GET /notifications/history` - Get notification history

## 📨 Messaging & Events

### Event-Driven Communication

Services communicate asynchronously via Kafka topics:

| Topic | Source | Listeners | Event Type |
|-------|--------|-----------|-----------|
| `user-events` | User Service | Notification Service | User created, updated, deleted |
| `vehicle-events` | Vehicle Service | Notification Service, Lender Service | Vehicle added, updated |
| `loan-events` | Lender Service | Notification Service | Loan created, approved, rejected |

### Example Event Flow

```
User Registration
    ↓
User Service publishes "user.created" event to Kafka
    ↓
Notification Service consumes event
    ↓
Sends welcome email to user
```

### Shared Events Library

The `customer-events` library contains common event models shared across services:
- User events
- Vehicle events
- Loan events
- Notification events

## 📚 API Documentation

Each service exposes its own API endpoints. Access them at:

- **User Service**: http://localhost:8081/swagger-ui.html (if Swagger enabled)
- **Vehicle Service**: http://localhost:8082/swagger-ui.html
- **Lender Service**: http://localhost:8083/swagger-ui.html
- **Notification Service**: http://localhost:8084/swagger-ui.html

### Eureka Dashboard

Monitor all running services at: http://localhost:8761

## 🧪 Testing

Run tests for all services:

```bash
mvn test
```

Run tests for a specific service:

```bash
cd user-service
mvn test
```

## 🤝 Contributing

1. Create a new branch from `main` or `notification`
2. Make your changes following the microservices patterns
3. Test thoroughly (unit tests, integration tests)
4. Ensure your code follows Spring Boot best practices
5. Submit a pull request with a clear description

## 🔐 Security Considerations

- Enable HTTPS in production
- Use environment variables for sensitive credentials (API keys, DB passwords)
- Implement authentication and authorization (JWT/OAuth2)
- Enable CORS only for trusted origins
- Validate all user inputs
- Use database encryption for sensitive data

## 📝 License

This project is currently unlicensed. Please refer to the repository for more information.

## 🤖 Troubleshooting

### Kafka Connection Issues
```bash
# Check Kafka is running
jps -l | grep Kafka

# Verify topics exist
kafka-topics.sh --list --bootstrap-server localhost:9092
```

### MySQL Connection Issues
```bash
# Verify MySQL is running
mysql -u root -p

# Check database exists
SHOW DATABASES;
```

### Service Registration Issues
- Ensure Eureka server is running on http://localhost:8761
- Check service `application.yml` has correct Eureka configuration
- Verify network connectivity between services

---

**Author**: greatGaurav101  
**Repository**: [twoWheelerFinance](https://github.com/greatGaurav101/twoWheelerFinance)  
**Last Updated**: June 2026  
**Branch**: notification (with full microservices architecture)
