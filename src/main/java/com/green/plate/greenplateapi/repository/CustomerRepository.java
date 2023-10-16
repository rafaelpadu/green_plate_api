package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUsuario_Id(Integer id);
}
