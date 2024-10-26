package com.lg5.spring.testcontainer.config;

import com.lg5.spring.testcontainer.container.AppCustomContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.output.OutputFrame;

@TestConfiguration
@ConditionalOnProperty(name = "testcontainers.app.enabled", havingValue = "true", matchIfMissing = true)
public class AppContainerCustomConfig extends BaseContainerCustomConfig {

    @Value("${application.traces.enabled: false}")
    private boolean traceEnabled;

    @Value("${application.image.name}")
    private String imageName;

    @Value("${application.log.destination.path: ./target/logs}")
    private String logDestinationPath;

    @Value("${application.log.source.path: /logs}")
    private String logSourcePath;


    @Bean
    AppCustomContainer appCustomContainer() {
        AppCustomContainer appCustomContainer = new AppCustomContainer(imageName);
        appCustomContainer.withFileSystemBind(logDestinationPath, logSourcePath, BindMode.READ_WRITE);
        if (traceEnabled) {
            appCustomContainer.withLogConsumer((OutputFrame outputFrame) -> System.out.println(outputFrame.getUtf8String()));
        }

        return appCustomContainer;
    }

}
