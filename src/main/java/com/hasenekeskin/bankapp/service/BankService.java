package com.hasenekeskin.bankapp.service;

import com.hasenekeskin.bankapp.dto.Balance;
import com.hasenekeskin.bankapp.dto.CustomerRequest;
import com.hasenekeskin.bankapp.dto.CustomerResponseDTO;
import com.hasenekeskin.bankapp.exception.CustomerAlreadyExistsException;
import com.hasenekeskin.bankapp.exception.CustomerNotFoundException;
import com.hasenekeskin.bankapp.model.Customer;
import com.hasenekeskin.bankapp.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;
    private final ModelMapper modelMapper ;

    public CustomerResponseDTO getCustomer(String tc) {

        Customer customer = bankRepository.findByTc(tc).orElseThrow(() -> new CustomerNotFoundException("Customer not found"
        ));

        return modelMapper.map(customer,CustomerResponseDTO.class);

    }

    public CustomerResponseDTO createCustomer(CustomerRequest newCustomer) {

        Optional<Customer> customerByTc = bankRepository.findByTc(newCustomer.getTc());
        if (customerByTc.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }
        Customer customer = modelMapper.map(newCustomer,Customer.class);
        bankRepository.save(customer);
        return modelMapper.map(customer,CustomerResponseDTO.class);


    }

    public void deleteCustomer(String tc) {
        bankRepository.deleteByTc(tc);
    }

    public Customer getCustomerBytc(String tc) {
        return bankRepository.findById(tc)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }


    public CustomerResponseDTO updateCustomer(CustomerRequest customerRequest) {
        Customer oldCustomer = bankRepository.findByTc(customerRequest.getTc()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));




        oldCustomer.setTc(customerRequest.getTc());
        oldCustomer.setPassword(customerRequest.getPassword());
        oldCustomer.setName(customerRequest.getName());
        oldCustomer.setSurname(customerRequest.getSurname());
        oldCustomer.setCustomerNo(customerRequest.getCustomerNo());
        oldCustomer.setDepartment(customerRequest.getDepartment());
        oldCustomer.setBalance(customerRequest.getBalance());
        bankRepository.save(oldCustomer);


        return modelMapper.map(oldCustomer,CustomerResponseDTO.class);



    }

    public void addBalance(String tc , Balance balance ) {
        Customer customer = bankRepository.findByTc(tc).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        if(customer.getBalance()==null){
            customer.setBalance(BigDecimal.valueOf(0));
        }
        customer.setBalance(customer.getBalance().add(balance.getBalance()));
        bankRepository.save(customer);

    }
}
