package com.lg5.spring.client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {



    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException();
            default -> new Exception("Generic error");
        };
    }
}