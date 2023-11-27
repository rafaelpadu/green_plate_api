package com.green.plate.greenplateapi.service.customer.impl;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.exception.GreenPlateException;
import com.green.plate.greenplateapi.model.Customer;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.repository.CustomerRepository;
import com.green.plate.greenplateapi.repository.UsuarioRepository;
import com.green.plate.greenplateapi.service.customer.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UsuarioRepository usuarioRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               UsuarioRepository usuarioRepository) {
        this.customerRepository = customerRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = mapCustomer(customerDTO);
        return mapCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Customer oldCustomer = customerRepository.findById(customerDTO.getId()).orElseThrow(GreenPlateException::new);
        oldCustomer.setCpf(customerDTO.getCpf());
        oldCustomer.setPhone(customerDTO.getPhone());
        oldCustomer.setFullName(customerDTO.getFullName());
        return mapCustomerDTO(customerRepository.save(oldCustomer));
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
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(GreenPlateException::new);
        return Optional.ofNullable(mapCustomerDTO(usuario.getCustomer()));
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
