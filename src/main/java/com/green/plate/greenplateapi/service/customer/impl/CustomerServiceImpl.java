package com.green.plate.greenplateapi.service.customer.impl;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.model.Customer;
import com.green.plate.greenplateapi.repository.CustomerRepository;
import com.green.plate.greenplateapi.service.customer.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Customer mapCustomer(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
       return modelMapper.map(customerDTO, Customer.class);
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = mapCustomer(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
}
