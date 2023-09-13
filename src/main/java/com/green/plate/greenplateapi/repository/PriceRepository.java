package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
