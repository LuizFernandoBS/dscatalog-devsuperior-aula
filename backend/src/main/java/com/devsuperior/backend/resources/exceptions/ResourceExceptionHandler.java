package com.devsuperior.backend.resources.exceptions;

import com.devsuperior.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,
                                                          HttpServletRequest request) {
        Instant time = Instant.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        String path = request.getRequestURI();

        StandardError standardError = new StandardError(time, status.value(),
                RESOURCE_NOT_FOUND, message, path);

        return ResponseEntity.status(status).body(standardError);
    }
}
