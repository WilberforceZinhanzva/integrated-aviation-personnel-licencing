package com.nimblecode.integratedaviationpersonellicencing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ExceptionBody> handleException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), HttpStatus.CONFLICT),HttpStatus.CONFLICT);
    }
}
