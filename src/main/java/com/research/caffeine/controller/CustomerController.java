package com.research.caffeine.controller;

import com.research.caffeine.model.entity.Customer;
import com.research.caffeine.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(@Qualifier("CustomerServiceImplement") CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<Long, Customer>> getAll() {
        Map<Long, Customer> mapOfCustomer = this.customerService.getAll();
        return new ResponseEntity<Map<Long, Customer>>(mapOfCustomer, HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> create(@RequestBody Customer customer){
        this.customerService.put(customer);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        Customer customer = this.customerService.get(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping(path = "/remove/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> removeCustomer(@PathVariable("id") Long id){
        this.customerService.remove(id);
        return new ResponseEntity<String>("removed", HttpStatus.OK);
    }
}
