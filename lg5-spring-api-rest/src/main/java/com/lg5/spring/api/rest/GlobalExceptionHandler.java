package com.lg5.spring.api.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * <h1>Caught Global Exception Handler</h1>
 * <h1>Catch Server errors </h1>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Unexpected error!");
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handleException(ValidationException validationException) {
        if (validationException instanceof ConstraintViolationException constraintViolationException) {
            final String validations = extractViolationsFromException(constraintViolationException);
            LOG.error(validations, validationException);
            return new ErrorDTO(BAD_REQUEST.getReasonPhrase(),validations);
        }
        final String exceptionMessage = validationException.getMessage();
        LOG.error(exceptionMessage, validationException);
        return new ErrorDTO(BAD_REQUEST.getReasonPhrase(),exceptionMessage);
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
