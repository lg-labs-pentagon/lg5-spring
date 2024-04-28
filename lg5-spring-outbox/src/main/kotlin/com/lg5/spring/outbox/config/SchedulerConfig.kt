package com.lg5.spring.outbox.config


import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
open class SchedulerConfig {

    /**
     * If I need add other property when JsonProperty does a serialization, and I want added other property
     * Then, is necessary create a Bean to override behavior in the JsonProperty process.
     * But, to this application it's unnecessary
     *
     * <!-- Only as example-->
     * <dependency>
     *       <groupId>org.springframework.boot</groupId>
     *       <artifactId>spring-boot-starter-json</artifactId>
     *     </dependency>
     */
    /*
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
    }*/
}