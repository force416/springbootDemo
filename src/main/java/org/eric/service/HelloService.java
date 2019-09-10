package org.eric.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eric.Repository.CustomerRepository;
import org.eric.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("helloService")
public class HelloService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getMsg() throws Exception {
        return customerRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Customer addCustomer(String firstName, String lastName) throws Exception {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Customer result = customerRepository.save(customer);

        return result;
    }
}
