package com.rahmania.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RahmaniaExceptionHandler  extends ResponseEntityExceptionHandler{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({RahmaniaException.class})
    public final ResponseEntity<String> handleException(RahmaniaException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({Exception.class})
    public final ResponseEntity<String> handleAllException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<String> handleBadRequest(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(getErrors(ex) ,HttpStatus.BAD_REQUEST);
    }

    private ValidationResult getErrors(MethodArgumentNotValidException ex) {
      ValidationResult result = new ValidationResult();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            result.getErrorList().add(new FieldErrorDTO(error.getField() , error.getDefaultMessage()));
        }
     return result;
    }
}