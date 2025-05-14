# 🩺 ClinicPulse

**ClinicPulse** é um sistema de gerenciamento de clínicas médicas moderno e modular, desenvolvido com arquitetura de microsserviços utilizando Java, Spring Boot, PostgreSQL, Docker e RabbitMQ.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- PostgreSQL (um banco por microsserviço)
- RabbitMQ
- Docker + Docker Compose
- Maven multimódulo (pom.xml pai e filhos)

---

## 🧱 Estrutura Inicial

```
clinicpulse/
├── pom.xml                   # POM pai
├── docker-compose.yml        # Infraestrutura com bancos, RabbitMQ, Zipkin
├── README.md
├── auth-service/             # Microsserviço de autenticação (em construção)
├── patient-service/
├── doctor-service/
├── appointment-service/
├── notification-service/
├── config-server/
├── discovery-server/
└── infra/
    ├── postgres/
    └── rabbitmq/
```

---

## 🧪 Como subir a infraestrutura

```bash
docker compose up -d
```

Verifique:
- RabbitMQ: http://localhost:15672 (guest / guest)
- Zipkin: http://localhost:9411
