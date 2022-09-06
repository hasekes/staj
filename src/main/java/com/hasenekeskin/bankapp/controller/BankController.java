package com.hasenekeskin.bankapp.controller;

import com.hasenekeskin.bankapp.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/customers")
public class BankController {

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        Customer customer1= new Customer("35353479616", 1234,"Hasene","Keskin"
                , 8081,"Pendik");
        List<Customer> customers= Arrays.asList(customer1);
        return new ResponseEntity<>(customers, OK);
    }



}
