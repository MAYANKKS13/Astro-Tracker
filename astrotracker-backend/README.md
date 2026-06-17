# Astro Tracker Backend 🚀

Backend service for Astro Tracker application built using Spring Boot.

The backend handles astronomical event fetching, persistence, REST APIs, subscriptions, and real-time event broadcasting using WebSockets.

---

## Technology Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* WebSocket
* STOMP
* SockJS
* Lombok

---

## Features

### Astronomical Event Management

* Fetch events from NASA API
* Convert external API response into application entities
* Store events in PostgreSQL
* Retrieve stored events through REST APIs

---

### Scheduler

The scheduler automatically fetches astronomical data periodically.

Flow:

```
Scheduler

    |

NASA API

    |

Convert API Response

    |

Save Event

    |

Publish WebSocket Event

```

---

## REST APIs

### Get All Events

```
GET /events
```

Returns all stored astronomical events.

---

### Create Event

```
POST /events
```

Creates a new astronomical event.

---

## Subscription APIs

### Subscribe User

```
POST /subscriptions
```

Example request:

```json
{
  "email":"test@gmail.com",
  "eventType":"ASTEROID_CLOSE_APPROACH"
}
```

---

### Unsubscribe

```
DELETE /subscriptions/{id}
```

---

## WebSocket

Connection endpoint:

```
ws://localhost:8080/ws
```

Topic:

```
/topic/events
```

Whenever a new event is detected, backend publishes:

```json
{
  "id":1,
  "eventType":"ASTEROID_CLOSE_APPROACH",
  "title":"2019 BZ",
  "source":"NASA"
}
```

---

## Database Tables

### astronomical_events

Stores astronomical events.

Columns:

```
id
event_type
title
start_time
end_time
visibility_info
source
```

### subscriptions

Stores user subscriptions.

Columns:

```
id
email
event_type
active
```

---

## Package Structure

```
controller
service
repository
entity
scheduler
external
websocket
dto
```

---

## Running Backend

Run:

```
./mvnw spring-boot:run
```

Server:

```
http://localhost:8080
```

---

## Future Improvements

* More astronomy APIs
* Email notification service
* Authentication and authorization
* Event history APIs
* Docker support
* Cloud deployment
