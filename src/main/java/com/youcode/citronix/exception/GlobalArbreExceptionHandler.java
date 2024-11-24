package com.youcode.citronix.exception;

import com.youcode.citronix.exception.arbreException.ArbreNotFoundException;
import com.youcode.citronix.exception.arbreException.ArbreNullORFutureDAtePlantationException;
import com.youcode.citronix.exception.arbreException.NotAlowMonthException;
import com.youcode.citronix.exception.champExceptions.ChampNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalArbreExceptionHandler {
    @ExceptionHandler(value={ArbreNotFoundException.class})
    public ResponseEntity<Object>  HandleArbreNotFoundException(ArbreNotFoundException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={ArbreNullORFutureDAtePlantationException.class})
    public ResponseEntity<Object>  HandleArbreNullORFutureDAtePlantationException(ArbreNullORFutureDAtePlantationException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value={NotAlowMonthException.class})
    public ResponseEntity<Object>  HandleNotAlowMonthException(NotAlowMonthException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
