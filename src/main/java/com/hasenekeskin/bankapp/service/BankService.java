package com.hasenekeskin.bankapp.service;

import com.hasenekeskin.bankapp.dto.Balance;
import com.hasenekeskin.bankapp.dto.CustomerRequest;
import com.hasenekeskin.bankapp.dto.CustomerResponseDTO;
import com.hasenekeskin.bankapp.exception.CustomerAlreadyExistsException;
import com.hasenekeskin.bankapp.exception.CustomerNotFoundException;
import com.hasenekeskin.bankapp.model.Customer;
import com.hasenekeskin.bankapp.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public CustomerResponseDTO getCustomer(String tc) {

        Customer customer = bankRepository.findByTc(tc).orElseThrow(() -> new CustomerNotFoundException("Customer not found"
        ));

        return CustomerResponseDTO.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .balance(customer.getBalance())
                .build();

    }

    public CustomerResponseDTO createCustomer(CustomerRequest newCustomer) {

        Optional<Customer> customerByTc = bankRepository.findByTc(newCustomer.getTc());
        if (customerByTc.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }
        Customer customer = new Customer();
        customer.setName(newCustomer.getName());
        customer.setTc(newCustomer.getTc());
        customer.setPassword(newCustomer.getPassword());
        customer.setSurname(newCustomer.getSurname());
        customer.setDepartment(newCustomer.getDepartment());
        customer.setCustomerNo(newCustomer.getCustomerNo());
        customer.setBalance(newCustomer.getBalance());

        Customer newCustomerObject = bankRepository.save(customer);

        return CustomerResponseDTO.builder()
                .surname(newCustomerObject.getSurname())
                .name(newCustomerObject.getName()).balance(newCustomerObject.getBalance()).build();
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

        oldCustomer.setName(customerRequest.getName());
        oldCustomer.setTc(customerRequest.getTc());
        oldCustomer.setPassword(customerRequest.getPassword());
        oldCustomer.setSurname(customerRequest.getSurname());
        oldCustomer.setDepartment(customerRequest.getDepartment());
        oldCustomer.setCustomerNo(customerRequest.getCustomerNo());
        Customer newCustomerObject = bankRepository.save(oldCustomer);


        return CustomerResponseDTO.builder()
                .surname(newCustomerObject.getSurname())
                .name(newCustomerObject.getName()).build();
    }

    public void addBalance(String tc , Balance balance ) {
        Customer customer = bankRepository.findByTc(tc).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        if(customer.getBalance()==null){
            customer.setBalance(BigDecimal.valueOf(0));
        }
        customer.setBalance(customer.getBalance().add(balance.getMiktar()));
        bankRepository.save(customer);

    }
}
