package com.example.carros.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.Set;

@RestControllerAdvice //vai permitir essa classe interceptar as exceções
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ //faz com que, se acontecer qualquer exception que declaramos nessa notação, este método será chamado
            EmptyResultDataAccessException.class
    })
    public ResponseEntity errorNotFound(Exception exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({ //faz com que, se acontecer qualquer exception que declaramos nessa notação, este método será chamado
            IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception exception){
        return ResponseEntity.badRequest().build();
    }
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       return new ResponseEntity<>(new ExceptionError("Operação não permitida"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    public class ExceptionError {
        private String error;
        ExceptionError(String error){
            this.error = error;
        }

        public String getError(){
            return error;
        }
    }

}
