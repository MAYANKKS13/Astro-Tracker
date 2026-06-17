# Astro Tracker 🚀

Astro Tracker is a full-stack real-time astronomical event monitoring application.

The application fetches astronomical events from external APIs, stores them in a database, and provides live updates to users using WebSockets.

The project is built using Spring Boot, PostgreSQL, WebSocket, and Angular.

---

## Features

### Backend

* Fetch astronomical events from NASA API
* Automatic event fetching using scheduled jobs
* Store astronomical events in PostgreSQL
* REST APIs for event management
* User subscription management
* Real-time event publishing using WebSockets

### Frontend

* Angular-based dashboard
* Display astronomical events
* Receive live event updates
* WebSocket integration with backend

---

## Architecture

```
                    NASA API
                       |
                       |
                Event Scheduler
                       |
              -------------------
              |                 |
              |                 |
          PostgreSQL       WebSocket
              |                 |
              |                 |
          REST APIs        Live Events
              |                 |
              -------------------
                       |
                    Angular UI
```

---

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* WebSocket (STOMP + SockJS)
* Maven

### Frontend

* Angular
* TypeScript
* STOMP Client
* SockJS

---

## Project Structure

```
astrotracker

│
├── astrotracker-backend
│
├── astrotracker-frontend
│
└── README.md

```

---

## Current Event Source

Currently integrated with:

* NASA Near Earth Object (NEO) API

Supported event type:

```
ASTEROID_CLOSE_APPROACH
```

Future event sources can include:

* Meteor showers
* Solar flares
* Lunar events
* Space weather events

---

## Running the Application

### Start Backend

Navigate to backend folder:

```
cd astrotracker-backend
```

Run:

```
./mvnw spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

### Start Frontend

Navigate to frontend folder:

```
cd astrotracker-frontend
```

Install dependencies:

```
npm install
```

Run:

```
ng serve
```

Frontend runs on:

```
http://localhost:4200
```

---

## Real-Time Event Flow

1. Scheduler periodically calls NASA API.
2. API response is converted into application events.
3. New events are stored in PostgreSQL.
4. Backend publishes events using WebSocket.
5. Angular dashboard receives and displays events instantly.

---

## Future Enhancements

* Multiple astronomy data sources
* Email notifications
* User authentication
* Event filtering
* Event search
* Astronomy visualizations
* Docker deployment
* Cloud deployment
