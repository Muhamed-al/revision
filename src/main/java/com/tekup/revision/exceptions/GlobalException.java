package com.tekup.revision.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Partie 4 : Global Exception
@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(exception.getMessage());
        response.put("status", 400);
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String , Object> response = new HashMap<>();

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();


        response.put("status", 400);
        response.put("message" , errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
