package com.research.caffeine.service.customer;

import com.research.caffeine.model.entity.Customer;

import java.util.Map;

public interface CustomerService {
    void put(Customer customer);
    Customer get(Long id);
    Map<Long, Customer> getAll();
    void remove(Long id);
}
