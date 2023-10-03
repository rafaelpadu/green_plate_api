package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.enums.ProductCategory;
import com.green.plate.greenplateapi.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProduct_ProductCategory(ProductCategory productCategory);
}
