package com.tony.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tony.crudspring.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;

/***
 * @RestControllerAdvice é usando para tratar exeptions, esta class fala para
 *                       todas as outras o que fazer com as exeptions
 */

@RestControllerAdvice
@SuppressWarnings("null")
public class ApplicationControlerAdvice {
    /** OBS estas Exceptions serve para TODOS os controllers */

    @ExceptionHandler(RecordNotFoundException.class) /**
                                                      * RecordNotFoundException.class Retorno dos erros Jakarta Been
                                                      * Validator
                                                      */
    @ResponseStatus(HttpStatus.NOT_FOUND) /* retorno 404 */
    public String handleNotFoundException(RecordNotFoundException ex) {
        return "Error: " + ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) /** Retorno 400 */
    public String methodArgumentsNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + " :\n");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String constraintViolationException(ConstraintViolationException ex) {
        /* return "Error  " + ex.getMessage(); ou a stream de errors */
        return ex.getConstraintViolations().stream().map(err -> err.getPropertyPath() + " " + err.getMessage())
                .reduce("", (acc, err) -> acc + err + " \n");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
      /*/  return ex.getPropertyName() + " Should be of type " + ex.getRequiredType().getName() ou*/
      if(ex != null && ex.getRequiredType() != null) {
        /**java.lang.long */
        String type = ex.getRequiredType().getName();

        /**Dividindo a string */
        String [] typeParts = type.split("\\.");

        /**Ficando com LONG */
        String typeName = typeParts[typeParts.length -1];
        return ex.getName() + " Should be of type " + typeName;

      }

      return "Arguments Type Not Valid";

    }
}
