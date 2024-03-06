package com.tony.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tony.crudspring.exception.RecordNotFoundException;

/***@RestControllerAdvice  é usando para tratar exeptions, esta class fala para todas as outras o que fazer com as exeptions*/

@RestControllerAdvice
public class ApplicationControlerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) /* retorno 404 */
    public String handleNotFoundException(RecordNotFoundException ex) {
         return "Error: " + ex.getMessage();
    }
    
}
