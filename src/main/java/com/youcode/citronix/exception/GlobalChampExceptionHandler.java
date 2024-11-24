package com.youcode.citronix.exception;

import com.youcode.citronix.exception.champExceptions.ChampNotFoundException;
import com.youcode.citronix.exception.champExceptions.ChampSuperficieInsuffisantException;
import com.youcode.citronix.exception.fermeExceptions.FermeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalChampExceptionHandler {
    @ExceptionHandler(value={ChampNotFoundException.class})
    public ResponseEntity<Object> HandleChampNotFoundException(ChampNotFoundException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={ChampSuperficieInsuffisantException.class})
    public ResponseEntity<Object> HandleChampSuperficieInsuffisantException(ChampSuperficieInsuffisantException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
