package com.hasenekeskin.bankapp.repository;

import com.hasenekeskin.bankapp.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends MongoRepository<Customer,String>{
    void deleteByTc(String tc);


    List<Customer> findAllByTc(String tc);
    Optional<Customer> findByTc(String tc);

}
