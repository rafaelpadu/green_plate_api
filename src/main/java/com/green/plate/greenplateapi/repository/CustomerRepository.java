package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
