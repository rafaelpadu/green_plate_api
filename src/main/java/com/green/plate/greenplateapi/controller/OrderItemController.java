package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.OrderItemDTO;
import com.green.plate.greenplateapi.service.orderItem.impl.OrderItemServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderItemDTO> saveOrderItem(@RequestBody @Valid OrderItemDTO orderItemDTO){
        try{
            orderItemService.saveOrderItem(orderItemDTO);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemDTO);
    }

    @GetMapping("/")
    public List<OrderItemDTO> getAllOrderItem(){
        return orderItemService.getAllOrder();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Integer id){
        return  orderItemService
                .getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
