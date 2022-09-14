package com.hasenekeskin.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg){
        super(msg);
    }

}
