package com.research.caffeine.service.customer;

import com.research.caffeine.model.entity.Customer;
import org.springframework.stereotype.Service;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("CustomerServiceImplement")
public class CustomerServiceImplement implements CustomerService {
    private Cache<Long, Customer> customerCache;

    public CustomerServiceImplement(){
        this.customerCache = Caffeine.newBuilder()
        .expireAfterAccess(60, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build();
    }

    @Override
    public void put(Customer customer) {
        this.customerCache.put(customer.getId(), customer);
    }

    @Override
    public Customer get(Long id) {
        Customer  customer = this.customerCache.getIfPresent(id);
        return customer;
    }

    @Override
    public Map<Long, Customer> getAll() {
        return this.customerCache.asMap();
    }

    @Override
    public void remove(Long id) {
        this.customerCache.invalidate(id);
    }
}
