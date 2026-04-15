# 🚀 SeniorStep - Modern Study Planner API

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)

## 📌 About the Project

**SeniorStep** is a robust API designed to manage study schedules and productivity. This project was developed as part of a technical modernization journey, applying the most demanding standards of the current software market.

The main goal is to allow the scheduling of study topics, ensuring no time conflicts and triggering automatic notifications to the user through different channels (Log, Telegram, E-mail).

---

## 🛠️ Tech Stack

*   **Language:** Java 25
*   **Framework:** Spring Boot 4.0.5
*   **Persistence:** Spring Data JPA / Hibernate
*   **Database:** PostgreSQL
*   **Documentation:** SpringDoc OpenAPI (Swagger)
*   **Testing:** JUnit 5, Mockito, AssertJ
*   **Infrastructure:** Docker & Docker Compose
*   **CI/CD:** GitHub Actions (Automated deploy to personal VPS)

---

## 🏗️ Engineering Pillars (Senior Mindset)

This project focuses on design patterns that ensure long-term scalability and maintenance:

1.  **SOLID Principles:** Rigorous application of Single Responsibility (SRP) and Open/Closed (OCP) principles.
2.  **Strategy Pattern:** Used for the notification engine, allowing the addition of new channels (SMS, WhatsApp) without changing the core business logic.
3.  **TDD (Test Driven Development):** Validation logic for periods and schedule conflicts developed using unit tests as a guide.
4.  **Clean Architecture / DDD Lite:** Package organization oriented by domain, isolating business rules from infrastructure.

---

## 🗺️ Development Roadmap

- [x] Initial project setup and Docker Compose.
- [ ] Domain modeling for scheduling (`StudySlot`).
- [ ] Implementation of the time conflict validation service.
- [ ] Development of the notification engine (Strategy Pattern).
- [ ] Telegram Bot integration for alerts.
- [ ] CI/CD pipeline configuration for personal VPS.
- [ ] Goal completion reporting features.

---

## 🚀 How to Run

### Prerequisites
*   Docker and Docker Compose installed.
*   Java 25 or higher.

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/RAShiguemoto/senior-step.git
   cd seniorstep