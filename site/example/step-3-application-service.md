# Paso 3: Application Service

## 3.1: DTOs de Entrada

```java
// CreateBlankCommand.java
package com.blanksystem.blank.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CreateBlankCommand(@NotNull UUID id) {}
```

## 3.2: DTOs de Salida

```java
// CreateBlankResponse.java
package com.blanksystem.blank.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.UUID;

@Builder
public record CreateBlankResponse(@NotNull UUID id, @NotNull String message) {}
```

## 3.3: Port de Entrada (Input Port)

```java
// BlankApplicationService.java (interface)
package com.blanksystem.blank.service.domain.ports.input.service;

import com.blanksystem.blank.service.domain.dto.create.CreateBlankCommand;
import com.blanksystem.blank.service.domain.dto.create.CreateBlankResponse;
import jakarta.validation.Valid;

public interface BlankApplicationService {
     CreateBlankResponse createBlank(
         @Valid CreateBlankCommand createBlankCommand
     );
}
```

## 3.4: Port de Salida (Output Port — Repository)

```java
// BlankRepository.java
package com.blanksystem.blank.service.domain.ports.output.repository;

import com.blanksystem.blank.service.domain.entity.Blank;
import java.util.Optional;
import java.util.UUID;

public interface BlankRepository {
     Blank createBlank(Blank blank);
     Optional<Blank> findbyId(UUID blankId);
}
```

## 3.5: Port de Salida (Output Port — Messaging)

```java
// BlankMessagePublisher.java
package com.blanksystem.blank.service.domain.ports.output.messaging;

import com.blanksystem.blank.service.domain.event.BlankCreatedEvent;

public interface BlankMessagePublisher {
     void publish(BlankCreatedEvent event);
}
```

## 3.6: Command Handler

```java
// BlankCreateCommandHandler.java
package com.blanksystem.blank.service.domain;

import com.blanksystem.blank.service.domain.dto.create.CreateBlankCommand;
import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.event.BlankCreatedEvent;
import com.blanksystem.blank.service.domain.exception.BlankDomainException;
import com.blanksystem.blank.service.domain.mapper.BlankDataMapper;
import com.blanksystem.blank.service.domain.ports.output.repository.BlankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class BlankCreateCommandHandler {

     private final BlankDomainService blankDomainService;
     private final BlankRepository blankRepository;
     private final BlankDataMapper blankDataMapper;

     public BlankCreateCommandHandler(
         BlankDomainService domainService,
         BlankRepository repository,
         BlankDataMapper dataMapper
     ) {
         this.blankDomainService = domainService;
         this.blankRepository = repository;
         this.blankDataMapper = dataMapper;
      }

     @Transactional
     public BlankCreatedEvent createBlank(CreateBlankCommand command) {
         Blank blank = blankDataMapper.createBlankCommandToBlank(command);
         BlankCreatedEvent event = blankDomainService.validateAndInitiateBlank(blank);
         Blank savedBlank = blankRepository.createBlank(blank);

         if (savedBlank == null) {
             throw new BlankDomainException(
                 "Could not save blank with id " + command.id()
             );
         }
         return event;
      }
}
```

## 3.7: Application Service Implementation

```java
// BlankApplicationServiceImpl.java
package com.blanksystem.blank.service.domain;

import com.blanksystem.blank.service.domain.dto.create.CreateBlankCommand;
import com.blanksystem.blank.service.domain.dto.create.CreateBlankResponse;
import com.blanksystem.blank.service.domain.event.BlankCreatedEvent;
import com.blanksystem.blank.service.domain.mapper.BlankDataMapper;
import com.blanksystem.blank.service.domain.ports.input.service.BlankApplicationService;
import com.blanksystem.blank.service.domain.ports.output.messaging.publisher.BlankMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class BlankApplicationServiceImpl implements BlankApplicationService {

     private final BlankCreateCommandHandler blankCreateCommandHandler;
     private final BlankDataMapper blankDataMapper;
     private final BlankMessagePublisher blankMessagePublisher;

     public BlankApplicationServiceImpl(
         BlankCreateCommandHandler commandHandler,
         BlankDataMapper dataMapper,
         BlankMessagePublisher messagePublisher
     ) {
         this.blankCreateCommandHandler = commandHandler;
         this.blankDataMapper = dataMapper;
         this.blankMessagePublisher = messagePublisher;
      }

     @Override
     public CreateBlankResponse createBlank(CreateBlankCommand command) {
         BlankCreatedEvent event = blankCreateCommandHandler.createBlank(command);
         blankMessagePublisher.publish(event);
         return blankDataMapper.blankToCreateBlankResponse(
             event.blank(), "Blank saved successfully!");
      }
}
```
