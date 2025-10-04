# Spring Boot SOAP Person Web Service

A simple SOAP web service for managing person records built with Spring Boot and Spring Web Services.

## Features

- Create, read, update, and delete person records
- SOAP-based web service using Spring Web Services
- In-memory storage (for demonstration purposes)

## Prerequisites

- Java 21 or later
- Maven 3.6.3 or later

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd spring-boot-soap-person-webservice
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## WSDL

Once the application is running, you can access the WSDL at:
```
http://localhost:8080/ws/persons.wsdl
```

## API Endpoints

- **Create Person**: `createPersonRequest`
- **Get Person**: `getPersonRequest`
- **Update Person**: `updatePersonRequest`
- **Delete Person**: `deletePersonRequest`

## Request/Response Examples

### Get Person Request
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:per="http://example.com/personservice">
   <soapenv:Header/>
   <soapenv:Body>
      <per:getPersonRequest>
         <per:id>1</per:id>
      </per:getPersonRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Technologies Used

- Spring Boot 3.5.6
- Spring Web Services
- JAXB for XML binding
- Maven for dependency management
