package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
