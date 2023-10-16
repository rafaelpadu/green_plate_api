package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.model.Customer;
import com.green.plate.greenplateapi.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.save(customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") Integer customerId) {
        return customerService
                .getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/secure/find-by-user-id")
    public ResponseEntity<CustomerDTO> getCustomerByUserID(@RequestHeader("userId") Integer userId) {
        return customerService
                .getCustomerByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
