# Paso 5: Data Access

## 5.1: JPA Entity

```java
// BlankEntity.java (Infrastructure Layer)
package com.blanksystem.blank.service.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blank", schema = "blank")
public class BlankEntity {
     @Id
     private UUID id;
}
```

## 5.2: JPA Repository Interface

```java
// BlankJPARepository.java
package com.blanksystem.blank.service.data.repository;

import com.blanksystem.blank.service.data.entity.BlankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BlankJPARepository extends JpaRepository<BlankEntity, UUID> {}
```

## 5.3: Mapper (MapStruct)

```java
// BlankDataAccessMapper.java
package com.blanksystem.blank.service.data.mapper;

import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.data.entity.BlankEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlankDataAccessMapper {

     BlankEntity blankToBlankEntity(Blank blank);
     Blank blankEntityToBlank(BlankEntity blankEntity);
}
```

## 5.4: Repository Adapter (Port Implementation)

```java
// BlankRepositoryImpl.java (Implementation del Output Port)
package com.blanksystem.blank.service.data.adapter;

import com.blanksystem.blank.service.data.mapper.BlankDataAccessMapper;
import com.blanksystem.blank.service.data.repository.BlankJPARepository;
import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.ports.output.repository.BlankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class BlankRepositoryImpl implements BlankRepository {

     private final BlankJPARepository repository;
     private final BlankDataAccessMapper blankDataAccessMapper;

     public BlankRepositoryImpl(
         BlankJPARepository repository,
         BlankDataAccessMapper blankDataAccessMapper
     ) {
         this.repository = repository;
         this.blankDataAccessMapper = blankDataAccessMapper;
      }

     @Override
     public Blank createBlank(Blank blank) {
         return blankDataAccessMapper.blankEntityToBlank(
             repository.save(
                 blankDataAccessMapper.blankToBlankEntity(blank)
             )
         );
      }

     @Override
     public Optional<Blank> findbyId(UUID blankId) {
         return repository.findById(blankId)
             .map(blankDataAccessMapper::blankEntityToBlank);
      }
```

## 5.5: Database Migration (Liquibase)

```yaml
db/changelog/db.changelog-master.yaml
databaseChangeLog:
     - include:
          file: db/changelog/ddl-v.0.0.1.yaml

db/changelog/ddl-v.0.0.1.yaml
databaseChangeLog:
     - changeSet:
          id: 01_blank_create_schema
          author: lg
          changes:
             - sql:
                  sql: "CREATE SCHEMA IF NOT EXISTS blank"
     - changeSet:
          id: 02_blank_create_blank_table
          author: lg
          changes:
             - createTable:
                  schemaName: blank
                  tableName: blank
                  columns:
                     - column:
                          name: id
                          type: UUID
                          constraints:
                             primaryKey: true
                             nullable: false
```
