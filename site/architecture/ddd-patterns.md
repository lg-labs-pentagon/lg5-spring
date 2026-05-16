# Patrones DDD

## Domain-Driven Design (DDD) en lg5-spring

lg5-spring implementa patrones **Domain-Driven Design (DDD)** en todas las capas del proyecto.

## DDD: Patrones Implementados

| Patrón DDD | Descripción | Ubicación |
|---|---|---|
| **AggregateRoot** | Raíz de un agregado que garantiza consistencia | `lg5-common-domain` |
| **Entity** | Objeto con identidad (UUID) | `your-domain-core` |
| **ValueObject** | Objeto inmutable sin identidad | `your-domain-core` |
| **DomainEvent** | Evento de dominio que ocurre | `your-domain-core` |
| **DomainException** | Excepción específica del dominio | `your-domain-core` |
| **Repository** | Port de salida para persistencia | `your-application-service` |
| **ApplicationService** | Use case coordinator | `your-application-service` |
| **Command** | DTOs de entrada (CQRS) | `your-application-service/dto` |
| **MessageListener** | Port de entrada para eventos | `your-application-service` |

## AggregateRoot

La clase base `AggregateRoot<T>` viene de `lg5-common-domain`:

```java
public class User extends AggregateRoot<UserId> {
     private String firstName;
     private String lastName;
      
     public User(UserId id, String firstName, String lastName) {
          super.setId(id);
          this.firstName = firstName;
          this.lastName = lastName;
      }
}
```

## Value Object

```java
public record UserId(UUID value) {
     public UserId {
          if (value == null) throw new IllegalArgumentException("ID cannot be null");
      }
}
```

## Domain Event

```java
public class UserCreatedEvent {
     private final User user;
     private final Instant occurredOn;

     public UserCreatedEvent(User user) {
          this.user = user;
          this.occurredOn = Instant.now();
      }
}
```

## Repository Pattern (Port de Salida)

```java
// Port interface (application layer)
public interface UserRepository {
     User save(User user);
     Optional<User> findById(UserId id);
}

// Adapter implementation (infrastructure layer)
public class UserRepositoryImpl implements UserRepository {
     private final UserJPARepository jpaRepository;
     private final UserMapper mapper;

     @Override
     public User save(User user) {
          return mapper.toAggregate(
               jpaRepository.save(mapper.toEntity(user))
          );
      }
}
```

## CQRS (Command Query Segregation)

```java
// COMMANDS (Write model)
public record CreateUserCommand(UUID id, String firstName, String lastName) {}

// RESPONSES (Read model)
public record UserResponse(UUID id, String firstName, String lastName) {}
```

## Ejemplo de Capa de Dominio

```
your-domain/
├── your-domain-core/                  # Pure domain
│    └── com/your/your/service/domain/
│        ├── entity/YourEntity.java       # AggregateRoot
│        ├── valueobject/YourId.java      # ValueObject
│        ├── event/YourCreatedEvent.java  # DomainEvent
│        ├── exception/YourException.java # DomainException
│        └── YourDomainService.java       # Domain logic
│
└── your-application-service/        # Application layer
     └── com/your/your/service/domain/
         ├── dto/
         │    ├── CreateYourCommand.java
         │    └── CreateYourResponse.java
         ├── ports/
         │    ├── input/service/YourApplicationService.java
         │    └── output/
         │        ├── repository/YourRepository.java
         │        └── messaging/YourEventPublisher.java
         ├── YourApplicationServiceImpl.java
         └── YourCommandHandler.java
```
