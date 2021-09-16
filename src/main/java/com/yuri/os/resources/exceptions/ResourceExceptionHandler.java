package com.yuri.os.resources.exceptions;

import com.yuri.os.services.exceptions.DataIntegratyViolationException;
import com.yuri.os.services.exceptions.ObjectNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandandError> objectNotFoundException(ObjectNotFoundException e) {
        StandandError error = 
        new StandandError(
                System.currentTimeMillis(), 
                HttpStatus.NOT_FOUND.value(), 
                e.getMessage()
            );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandandError> DataIntegratyViolationException(DataIntegratyViolationException e) {
        StandandError error = 
        new StandandError(
                System.currentTimeMillis(), 
                HttpStatus.BAD_REQUEST.value(), 
                e.getMessage()
            );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandandError> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationError error = 
        new ValidationError(
                System.currentTimeMillis(), 
                HttpStatus.BAD_REQUEST.value(), 
                "Erro na validação dos dados."
            );

        for (FieldError err : e.getBindingResult().getFieldErrors()) {
            error.addError(err.getField(), err.getDefaultMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    
}
