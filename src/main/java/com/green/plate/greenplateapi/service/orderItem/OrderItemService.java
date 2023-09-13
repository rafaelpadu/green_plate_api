package com.green.plate.greenplateapi.service.orderItem;

import com.green.plate.greenplateapi.dto.OrderItemDTO;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> getAllOrder();

    Optional<OrderItemDTO> getOrderItemById(Integer id);

}
