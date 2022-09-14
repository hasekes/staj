package com.hasenekeskin.bankapp.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}
