package com.darwinruiz.springboot.errors.springbooterrors.configurations;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.darwinruiz.springboot.errors.springbooterrors.configurations.exceptions.UserNotFoundException;
import com.darwinruiz.springboot.errors.springbooterrors.dtos.configurations.Error;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<Error> divisionByZero(Exception e, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Division by zero is not allowed.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFound(Exception e, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Resource not found.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Error> numberFormat(Exception e, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Invalid number format.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({NullPointerException.class, HttpMessageNotWritableException.class})
    public ResponseEntity<Error> nullPointer(Exception e, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Null pointer exception occurred.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException e, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("User not found.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
