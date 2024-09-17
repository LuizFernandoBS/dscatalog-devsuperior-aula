package com.devsuperior.backend.resources.exceptions;

import com.devsuperior.backend.services.exceptions.DatabaseException;
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
    private static final String DATABASE_EXCEPTION = "Database exception";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,
                                                          HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError standardError = new StandardError(Instant.now(), status.value(),
                RESOURCE_NOT_FOUND, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> integrityViolation(DatabaseException e,
                                                            HttpServletRequest request) {

        return ResponseEntity.ok().body(new StandardError(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), DATABASE_EXCEPTION, e.getMessage(),
                request.getRequestURI()));
    }
}
