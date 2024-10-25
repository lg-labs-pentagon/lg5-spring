package com.lg5.spring.client.config;

import com.lg5.spring.client.exception.CustomErrorDecoder;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Value("${third.basic.auth.username}")
    private String username;
    @Value("${third.basic.auth.password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
