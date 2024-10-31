package com.lg5.spring.testcontainer.config;

import com.lg5.spring.testcontainer.container.AppCustomContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.output.OutputFrame;

import java.util.Map;

import static com.lg5.spring.testcontainer.util.Constant.APP_PORT_DEFAULT;

@TestConfiguration
@ConditionalOnProperty(name = "testcontainers.app.enabled", havingValue = "true", matchIfMissing = true)
public class AppContainerCustomConfig extends BaseContainerCustomConfig {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${application.server.port: " + APP_PORT_DEFAULT + "}")
    private int serverPort;

    @Value("${application.traces.console.enabled: false}")
    private boolean traceConsoleEnabled;

    @Value("${application.traces.file.enabled: false}")
    private boolean traceFileEnabled;

    @Value("${application.image.name}")
    private String imageName;

    @Value("${application.log.destination.path: ./target/logs}")
    private String logDestinationPath;

    @Value("${application.log.source.path: /logs}")
    private String logSourcePath;


    @Bean
    AppCustomContainer appCustomContainer() {
        AppCustomContainer appCustomContainer = new AppCustomContainer(imageName);
        appCustomContainer.withAppEnvVars(appWithEnv());

        setupLogFiles(appCustomContainer);

        setupConsoleTraces(appCustomContainer);

        return appCustomContainer;
    }

    private void setupConsoleTraces(AppCustomContainer appCustomContainer) {
        if (traceConsoleEnabled) {
            appCustomContainer.withLogConsumer((OutputFrame outputFrame) -> LOG.info(outputFrame.getUtf8String()));
        }
    }

    private void setupLogFiles(AppCustomContainer appCustomContainer) {
        if(traceFileEnabled){
            appCustomContainer.withFileSystemBind(logDestinationPath, logSourcePath, BindMode.READ_WRITE);
            appCustomContainer.getEnvMap().put("log.path", "./"+logSourcePath);
        }
    }

    private Map<String, String> appWithEnv() {
        return Map.of(
                "SERVER_PORT", String.valueOf(serverPort)
        );
    }

}
