package com.hasenekeskin.bankapp.controller;

import com.hasenekeskin.bankapp.dto.Balance;
import com.hasenekeskin.bankapp.dto.CustomerRequest;
import com.hasenekeskin.bankapp.dto.CustomerResponseDTO;
import com.hasenekeskin.bankapp.model.Customer;
import com.hasenekeskin.bankapp.service.BankService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Validated
public class BankController {

    private final BankService bankService;


    @GetMapping("/{tc}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable String tc) {
        return new ResponseEntity<>(bankService.getCustomer(tc), OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequest newCustomer) {

        return new ResponseEntity<>(bankService.createCustomer(newCustomer), CREATED);
    }

    @PutMapping
    public  ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerRequest customerRequest){
        return  new ResponseEntity<>(bankService.updateCustomer(customerRequest), ACCEPTED);
    }



    @DeleteMapping("/{tc}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String tc) {
        bankService.deleteCustomer(tc);
        return new ResponseEntity<>(OK);
    }



    @PutMapping("/{tc}")
    public ResponseEntity<Void> addBalance (@PathVariable String tc, @RequestBody Balance balance){
            bankService.addBalance(tc,balance);
            return new ResponseEntity<>(HttpStatus.OK);
    }


}
