package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
