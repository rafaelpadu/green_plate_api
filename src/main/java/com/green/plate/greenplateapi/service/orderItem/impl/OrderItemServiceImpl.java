package com.green.plate.greenplateapi.service.orderItem.impl;

import com.green.plate.greenplateapi.dto.OrderItemDTO;
import com.green.plate.greenplateapi.model.OrderItem;
import com.green.plate.greenplateapi.repository.OrderItemRepository;
import com.green.plate.greenplateapi.service.orderItem.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {
        return mapToOrderItemDTO(orderItemRepository.save(mapToOrderItem(orderItemDTO)));
    }

    @Override
    public List<OrderItemDTO> getAllOrder() {
        return orderItemRepository.findAll().stream().map(this::mapToOrderItemDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderItemDTO> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id).map(this::mapToOrderItemDTO);
    }
    private OrderItem mapToOrderItem(OrderItemDTO orderItemDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderItemDTO, OrderItem.class);
    }
    private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderItem, OrderItemDTO.class);
    }
}
