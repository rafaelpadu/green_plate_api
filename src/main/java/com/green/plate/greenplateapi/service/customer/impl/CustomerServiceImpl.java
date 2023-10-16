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


    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = mapCustomer(customerDTO);
        return mapCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<CustomerDTO> getCustomerByUserId(Integer userId) {
        return customerRepository.findByUsuario_Id(userId).map(this::mapCustomerDTO);
    }

    private Customer mapCustomer(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, Customer.class);
    }

    private CustomerDTO mapCustomerDTO(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
