package com.israelgda.sistemacomprasapi.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;
//        error.setTimestamp(Instant.now());
//        error.setStatus(status.value());
//        error.setError("Entity Not Found");
//        error.setPath(request.getRequestURI());
//        error.setMessage(exception.getMessage());
        return ResponseEntity.status(status).body(error);
    }
}
