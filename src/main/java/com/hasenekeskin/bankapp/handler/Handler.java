package com.hasenekeskin.bankapp.handler;

import com.hasenekeskin.bankapp.exception.CustomerAlreadyExistsException;
import com.hasenekeskin.bankapp.exception.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Handler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> HandleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<String> HandleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

    }
}
