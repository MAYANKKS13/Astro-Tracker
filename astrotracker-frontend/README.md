# Astro Tracker Frontend 🚀

Angular frontend application for displaying real-time astronomical events.

The frontend communicates with Spring Boot backend using REST APIs and WebSockets.

---

## Technology Stack

* Angular
* TypeScript
* STOMP Client
* SockJS

---

## Features

* Real-time astronomical event dashboard
* WebSocket based live updates
* Display incoming events
* Connect with backend REST APIs

---

## Application Flow

```
Angular Dashboard

        |

        |

GET /events

        |

Load existing events


        +

        |

WebSocket Connection

        |

Receive new events instantly

```

---

## WebSocket Configuration

Backend endpoint:

```
ws://localhost:8080/ws
```

Subscription topic:

```
/topic/events
```

---

## Project Structure

```
src/app


├── dashboard

│       Display events


├── service

│       WebSocket communication


├── models

│       Event models


```

---

## Running Frontend

Install dependencies:

```
npm install
```

Start application:

```
ng serve
```

Frontend runs on:

```
http://localhost:4200
```

---

## Real-Time Updates

When backend detects a new astronomical event:

```
NASA API

    |

Backend Scheduler

    |

WebSocket

    |

Angular Dashboard

```

The dashboard automatically updates without refreshing the page.

---

## Future Improvements

* Angular Material UI
* Event search
* Event filtering
* Event categories
* Charts and visualizations
* Interactive astronomy maps
