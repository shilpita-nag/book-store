package com.sample.bookstore.controller;

import com.sample.bookstore.Exception.CustomerNotFoundException;
import com.sample.bookstore.db.model.Customer;
import com.sample.bookstore.db.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public Iterable findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer id : " + id + " not found");
        }
        return customerOptional;
    }

}
