package com.lg5.spring.integration.test.boot;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "local"})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class Lg5TestBootPortNone {

}
