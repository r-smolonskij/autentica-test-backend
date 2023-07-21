# Autentica Test Backend

Autentica Test Backend is a RESTful API built with Spring Boot, serving as the backend for a web application. It provides endpoints to manage devices and their properties.

## Prerequisites

- Java 11
- Apache Maven

## Getting Started

Clone the repository:

```bash
git clone https://github.com/r-smolonskij/autentica-test-backend.git
cd autentica-test-backend
```

Run the application:

```bash
mvn spring-boot:run
```

## Endpoints

## Devices 

### GET /device

Get a list of all devices.

### GET /device/{id}

Get details of a specific device by ID.

### POST /device

Add a new device.

### PUT /device/{id}

Update details of a specific device by ID.

### DELETE /device/{id}

Delete a specific device by ID.

## Inquiries 

### GET /inquiry

Get a list of all devices.

### GET /inquiry/{id}

Get details of a specific device by ID.

### POST /inquiry

Add a new device.

### PUT /inquiry/{id}

Update details of a specific device by ID.

### DELETE /inquiry/{id}

Delete a specific device by ID.
