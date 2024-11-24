package com.youcode.citronix.exception;

import com.youcode.citronix.exception.RecolteDetailException.DoubleRecoltingInThSAmeSAion;
import com.youcode.citronix.exception.recolteException.RecoltNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalRecoltDeatailException {

    @ExceptionHandler(value={DoubleRecoltingInThSAmeSAion.class})
    public ResponseEntity<Object> HandleDoubleRecoltingInThSAmeSAion(DoubleRecoltingInThSAmeSAion ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
