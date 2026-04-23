# API Guardrails - Backend Assignment

## My Extra Features (Beyond Assignment)

1. DTOs to hide unnecessary info from User
2. One Human can like a Post only once (Redis check)
3. Global Exception Handler for clean error responses
4. Enums for AuthorType (BOT/HUMAN) instead of raw strings
5. AuthorType checked on every interaction
6. Service Interface + Implementation pattern followed
7. Redis String Serializer configured for human-readable keys
8. Separate RedisService class for all Redis operations
9. Builders used while sending Responses
10. Validation on every Request DTO
11. Generic ResponseEntity so exception body can be set properly
12. Profile based configuration (application-dev.properties)

## Assignment Guardrails Implemented

1. **Horizontal Cap** - Max 100 bot comments per post (Redis INCR atomic operation)
2. **Vertical Cap** - Comment thread max 20 levels deep
3. **Cooldown Cap** - Bot cannot interact with same human within 10 minutes (Redis TTL)
4. **Virality Score** - Bot comment +1, Human comment +50, Human like +20
5. **Notification Engine** - Smart batching, 15 min cooldown, pending notifications list
6. **CRON Sweeper** - Runs every 5 minutes, summarizes pending notifications

## How Thread Safety is Guaranteed

Redis **INCR** command is atomic by nature — even if 200 concurrent requests hit at the same millisecond, Redis processes each INCR one by one. So bot_count will stop exactly at 100, not 101. No HashMap or static variables are used anywhere — all state lives in Redis.

## Tech Stack

- Java 21
- Spring Boot 3.5.x
- PostgreSQL 16
- Redis
- Maven

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/posts | Create a new post |
| POST | /api/posts/{postId}/comments | Add a comment to a post |
| POST | /api/posts/{postId}/like | Like a post |

## Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/AadityaPrajapat/api-guardrails.git
```

2. Copy example properties file and add your password
```bash
cp src/main/resources/application-dev.properties.example src/main/resources/application-dev.properties
```

3. Run the application
```bash
mvn spring-boot:run
```

## Docker (Optional)

```bash
docker-compose up -d
```
