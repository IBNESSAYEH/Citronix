package com.youcode.citronix.exception;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.youcode.citronix.exception.fermeExceptions.FermeException;
import com.youcode.citronix.exception.fermeExceptions.FermeNotFoundException;
import com.youcode.citronix.exception.fermeExceptions.NombreChampInsuffisant;
import com.youcode.citronix.exception.fermeExceptions.SuperficieInsuffisanteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalFermeExceptionHandler {

    @ExceptionHandler(value={FermeException.class})
    public ResponseEntity<Object> HandleFermeException(FermeException ex, WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {SuperficieInsuffisanteException.class})
    public ResponseEntity<Object> handleSuperficieInsuffisanteException(SuperficieInsuffisanteException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FermeNotFoundException.class})
    public ResponseEntity<Object> handleFermeNotFoundException(FermeNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {NombreChampInsuffisant.class})
    public ResponseEntity<Object> handleNombreChampInsuffisant(NombreChampInsuffisant ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}