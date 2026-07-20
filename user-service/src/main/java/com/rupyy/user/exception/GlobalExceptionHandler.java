package com.rupyy.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ur){
        return new ResponseEntity<>(ur.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAnyUserFoundException.class)
    ResponseEntity<String> handleNoUserFoundException(NotAnyUserFoundException nur){
        return new ResponseEntity<>(nur.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity handleAllException(Exception ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailSentFailException.class)
    ResponseEntity handleEmailSentFailException(EmailSentFailException eml){
        return new ResponseEntity(eml.getMessage(),HttpStatus.NO_CONTENT);
    }

}
