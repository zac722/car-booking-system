# Full-Stack Development Task: Online Booking System
A simple web-based online booking system that allows customers to browse available cars, book a car, and manage their bookings

## Application Architecture and Technologies

This project is built using a modern, scalable architecture:

- Backend: Java with Spring Boot
  - RESTful API endpoints
  - Business logic implementation
  - Database interaction

- Frontend: Vue.js with Vuetify
  - Responsive user interface
  - State management
  - API integration

- Database: PostgreSQL
  - Robust, open-source relational database

## Build and Run
```
$ docker-compose build
$ docker-compose up
```

## Accessing the Website

Open your web browser and go to:  
http://localhost:4173/

## Login Credentials

Use the following credentials to log in or sign up a new account:

- Username: `User1`
- Password: `!QAZ2wsx`

## Accessing the Admin Page

1. Log in with admin credentials:
   - Username: `Admin`
   - Password: `!QAZ2wsx`

2. Navigate to the admin page:  
    http://localhost:4173/admin

## Viewing Database Data

To view the database data:

1. Navigate to pgAdmin page:  
http://localhost:5050

2. Login with the following credentials:
   - Username: `pg@admin.com`
   - Password: `password`

3. Right-click "Servers" in the top-left corner and select "Register" -> "Server..."

4. Name your connection

5. Change to the "Connection" tab and add the connection details:

- Hostname: `postgres`
- Port: `5432`
- Username: `postgres`
- Password: `password`

## Stop the containers
```
$ docker compose down
```