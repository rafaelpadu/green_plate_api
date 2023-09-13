package com.green.plate.greenplateapi.service.pedido.impl;

import com.green.plate.greenplateapi.dto.OrderItemDTO;
import com.green.plate.greenplateapi.dto.PedidoDTO;
import com.green.plate.greenplateapi.model.Pedido;
import com.green.plate.greenplateapi.repository.PedidoRepository;
import com.green.plate.greenplateapi.service.orderItem.impl.OrderItemServiceImpl;
import com.green.plate.greenplateapi.service.pedido.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final OrderItemServiceImpl orderItemService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, OrderItemServiceImpl orderItemService) {
        this.pedidoRepository = pedidoRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.save(mapToPedido(pedidoDTO));
        for (OrderItemDTO orderItemDTO : pedidoDTO.getOrderItemList()) {
            orderItemDTO.setPedidoId(pedido.getId());
            orderItemService.saveOrderItem(orderItemDTO);
        }
        return mapToPedidoDTO(pedido);
    }

    @Override
    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll().stream().map(this::mapToPedidoDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<PedidoDTO> getPedidoById(Integer id) {
        return pedidoRepository.findById(id).map(this::mapToPedidoDTO);
    }

    private Pedido mapToPedido(PedidoDTO pedidoDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedidoDTO, Pedido.class);
    }
    private PedidoDTO mapToPedidoDTO(Pedido pedido){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedido, PedidoDTO.class);
    }
}
