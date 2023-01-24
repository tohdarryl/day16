# Workshop 16

## For Postman
```
{
    "name": "Mastermind",
    "pieces": {
        "decoding_board": {
            "total_count": 1
        },
        "pegs": {
            "total_count": 102,
            "types": [
                {
                    "type": "code",
                    "count": 72
                },
                {
                    "type": "key",
                    "count": 30
                }
            ]
        },
        "rulebook": {
            "total_count": 1,
            "file": "rulebook-ultimate-mastermind.pdf"
        }
    }
}
```
## Setting up application.properties
export REDISHOST=localhost ${REDISHOST} <br>
export REDISPORT=6379 ${REDISPORT}

```
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.username=
spring.redis.password=
spring.redis.client.type=jedis
```

## Springboot Dependencies (spring initializr)
```
1. Spring Web
2. Spring Boot DevTools
3. Thymeleaf
4. Validation
5. Spring Data Redis (Access+Driver)

```

## JSON Dependency Injector
```
<dependency> 
            <groupId>org.glassfish</groupId>
            <artifactId>jakarta.json</artifactId> 
            <version>2.0.1</version>
</dependency>

```
## Redis Dependency Injector
```
<dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.3.1</version>
</dependency>
```

## Redis Server

On Terminal: To start Redis Server 
```
redis-server
```

On Separate Terminal <br>
To check Redis Server 
```
redis-cli
```