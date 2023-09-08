package com.green.plate.greenplateapi.service.customer;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    void save(CustomerDTO customerDTO);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Integer customerId);
}
