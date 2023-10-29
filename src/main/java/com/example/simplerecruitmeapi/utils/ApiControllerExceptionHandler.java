package com.example.simplerecruitmeapi.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ApiControllerExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife) {
            String fieldName = ife.getPath().get(0).getFieldName();
            Object value = ife.getValue();
            String targetType = ife.getTargetType().getSimpleName();

            String errorMsg = String.format("Invalid value '%s' for field '%s'. It should be of type '%s'.", value, fieldName, targetType);
            return new ResponseEntity<>(Collections.singletonList(errorMsg), HttpStatus.BAD_REQUEST);
        }

        // For other JSON parsing errors
        return new ResponseEntity<>(Collections.singletonList("Invalid request payload format."), HttpStatus.BAD_REQUEST);
    }
}
