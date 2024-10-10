package com.lg5.mdc;

import jakarta.annotation.PostConstruct;
import org.slf4j.MDC;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;

@Configuration
public class BaseMDC {

    @PostConstruct
    public void init() {
        MDC.put("springVersion", SpringVersion.getVersion());
        MDC.put("javaVersion", System.getProperty("java.version"));
        MDC.put("SpringBootVersion", SpringBootVersion.getVersion());
    }
}
