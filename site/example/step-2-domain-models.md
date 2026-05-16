# Paso 2: Domain Models

## 2.1: Aggregate Root: Blank

```java
// blank-domain-core/src/main/java/.../domain/entity/Blank.java
package com.blanksystem.blank.service.domain.entity;

import com.blanksystem.blank.service.domain.exception.BlankDomainException;
import com.blanksystem.blank.service.domain.valueobject.BlankId;
import com.labs.lg.pentagon.common.domain.entity.AggregateRoot;

public class Blank extends AggregateRoot<BlankId> {

     public Blank(BlankId blankId) {
         super.setId(blankId);
     }

     public void validate() {
         if (getId() == null) {
             throw new BlankDomainException(
                 "The Blank to create is invalid"
             );
         }
     }
}
```

## 2.2: ValueObject: BlankId

```java
// blank-domain-core/src/main/java/.../domain/valueobject/BlankId.java
package com.blanksystem.blank.service.domain.valueobject;

import java.util.UUID;

public record BlankId(UUID value) {
     public BlankId {
         if (value == null) {
             throw new IllegalArgumentException("ID cannot be null");
         }
     }

     public BlankId() {
         this(UUID.randomUUID());
     }
}
```

## 2.3: DomainEvent: BlankCreatedEvent

```java
// blank-domain-core/src/main/java/.../domain/event/BlankCreatedEvent.java
package com.blanksystem.blank.service.domain.event;

import com.blanksystem.blank.service.domain.entity.Blank;

public record BlankCreatedEvent(Blank blank) {}
```

## 2.4: DomainException: BlankDomainException

```java
package com.blanksystem.blank.service.domain.exception;

public class BlankDomainException extends RuntimeException {

     public BlankDomainException(String message) {
         super(message);
     }
}
```

## 2.5: Domain Service

```java
// blank-domain-core/src/main/java/.../domain/BlankDomainService.java
package com.blanksystem.blank.service.domain;

import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.event.BlankCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class BlankDomainService {

     public BlankCreatedEvent validateAndInitiateBlank(Blank blank) {
         blank.validate();
         return new BlankCreatedEvent(blank);
     }
}
```

## Estructura Resultante

```
blank-domain-core/
└── com/blanksystem/blank/service/domain/
     ├── entity/
     │    └── Blank.java                   # AggregateRoot
     ├── valueobject/
     │    └── BlankId.java                 # ValueObject (record)
     ├── event/
     │    └── BlankCreatedEvent.java       # DomainEvent
     ├── exception/
     │    └── BlankDomainException.java    # DomainException
     ├── BlankDomainService.java           # Domain logic
     └── BlankServiceConfigData.java       # Config properties
```
