package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.model.Customer;
import com.green.plate.greenplateapi.service.customer.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ApiResponse(code = 404, message = "Cliente não encontrado")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") Integer customerId) {
        return customerService
                .getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
