package com.redislabs.demo.controller;

import com.redislabs.demo.model.Customer;
import com.redislabs.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        Optional<Customer> optCustomer = repository.findById(id);
        if (optCustomer.isPresent())
            return optCustomer.get();
        else {
            // create the customer
            Customer customer = new Customer(id, "123456-"+id, "John Doe (" + id + ")", new Date());
            customer = repository.save(customer);
            optCustomer = repository.findById(id);
            return optCustomer.get();
        }
    }

}
