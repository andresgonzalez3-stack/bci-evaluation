package com.bci.evaluation.commons.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(DataIntegrityViolationException ex) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
                        Timestamp.from(Instant.now()));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(
            ConstraintViolationException ex) {

        StringBuilder errorMessage = new StringBuilder();

        for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
            errorMessage.append(error.getPropertyPath().toString()).append(": ").append(error.getMessage());
        }

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST.value(),
                        Timestamp.from(Instant.now()));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        StringBuilder errorMessage = new StringBuilder();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessage.append("[").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("] ");
        }

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST.value(),
                        Timestamp.from(Instant.now()));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) {
        ExceptionResponse error =
                new ExceptionResponse("Unexpected error",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), Timestamp.from(Instant.now()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
