package com.lg5.spring.testcontainer;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import({TestContainerReusedConfig.class})
public abstract class AbstractContainerReusedTest {
    @LocalServerPort
    int port;

    protected RequestSpecification requestSpecification;

    @BeforeEach
    void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
