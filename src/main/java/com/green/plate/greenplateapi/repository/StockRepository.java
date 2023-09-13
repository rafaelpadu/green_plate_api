package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
