# Unit Tests

## Framework: JUnit 5 + Mockito

Los **Unit Tests** en lg5-spring validan la lógica pura del dominio sin dependencias externas.

## Ejemplo: Validación de AggregateRoot

```java
// BlankTest.java
package com.blanksystem.blank.service.domain;

import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.exception.BlankDomainException;
import com.blanksystem.blank.service.domain.valueobject.BlankId;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BlankTest {

      @Test
     void it_should_create_a_blank_successfully() {
         Blank blank = new Blank(new BlankId(UUID.randomUUID()));
         assertDoesNotThrow(() -> blank.validate());
       }

      @Test
     void it_should_throw_exception_when_blank_id_is_null() {
         Blank blank = new Blank(new BlankId(null));
         assertThrows(BlankDomainException.class, () -> blank.validate());
       }
}
```

## Ejemplo: Testing de Mapper (MapStruct)

```java
// BlankDataAccessMapperTest.java
package com.blanksystem.blank.service.data;

import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.valueobject.BlankId;
import com.blanksystem.blank.service.data.entity.BlankEntity;
import com.blanksystem.blank.service.data.mapper.BlankDataAccessMapper;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BlankDataAccessMapperTest {

      private final BlankDataAccessMapper mapper =
          new BlankDataAccessMapperImpl();

      @Test
     void blankToBlankEntity_should_map_correctly() {
         Blank blank = new Blank(new BlankId(UUID.randomUUID()));
         BlankEntity entity = mapper.blankToBlankEntity(blank);

         assertNotNull(entity);
         assertEquals(blank.getId().getValue(), entity.getId());
       }

      @Test
     void blankEntityToBlank_should_map_correctly() {
         BlankEntity entity = BlankEntity.builder()
              .id(UUID.randomUUID())
              .build();
         Blank blank = mapper.blankEntityToBlank(entity);

         assertNotNull(blank);
         assertEquals(entity.getId(), blank.getId().getValue());
       }
}
```

## Ejecución

```bash
# Ejecutar todos los unit tests
make run-unit-test

# Ejecutar un unit test específico
make run-ut-spec TEST_NAME=BlankTest
```
