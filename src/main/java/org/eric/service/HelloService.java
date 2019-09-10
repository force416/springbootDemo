package org.eric.service;

import org.eric.Repository.CustomerRepository;
import org.eric.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("helloService")
public class HelloService {

    @Autowired
    private CustomerRepository customerRepository;

    public String getMsg() {
        List<Customer> customers = customerRepository.findAll();

        Customer customer = customers.get(1);
        String name = customer == null ? "no name" : customer.getFirstName();

        return String.format("hello %s", name);
    }
}
