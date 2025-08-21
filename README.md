# Hospital Microservices (Windows-friendly, no Docker/Kafka)

Java 17 • Spring Boot 3.3 • Spring Cloud 2024.0.1 • Gradle • MySQL • JWT • Swagger • React UI

## Modules (ports)
- eureka-server (8761)
- api-gateway (8080)
- doctor-service (8081)
- patient-service (8082)
- appointment-service (8083)
- billing-service (8084)
- frontend-react (React dev server 3000)

## Prereqs
- JDK 17
- Gradle (wrapper auto-download by IntelliJ)
- MySQL running locally (3306)

Create databases once:
```sql
CREATE DATABASE doctor_db;
CREATE DATABASE patient_db;
CREATE DATABASE appointment_db;
CREATE DATABASE billing_db;
```

Set env vars if needed (Windows PowerShell):
```ps1
$env:MYSQL_HOST="127.0.0.1"
$env:MYSQL_PORT="3306"
$env:MYSQL_USER="root"
$env:MYSQL_PASSWORD="yourpass"
```

## Run order
1) Run `eureka-server` (port 8761)
2) Run `api-gateway` (port 8080)
3) Run each service: doctor-service, patient-service, appointment-service, billing-service
4) Open React UI: `frontend-react` -> `npm install` -> `npm start` (http://localhost:3000)

## API Through Gateway
- Doctors: `http://localhost:8080/doctor/api/v1/doctors`
- Patients: `http://localhost:8080/patient/api/v1/patients`
- Appointments: `http://localhost:8080/appointment/api/v1/appointments`
- Billing: `http://localhost:8080/billing/api/v1/billing`

## Auth
Get token from any service:
```
POST http://localhost:8081/api/auth/login
{ "username": "demo", "password": "demo" }
```
Send `Authorization: Bearer <token>` for POST/PATCH/DELETE.
GET endpoints are open by default.

## Swagger
- `http://localhost:8081/swagger-ui.html` (or service port accordingly)
