package com.green.plate.greenplateapi.service.pedido.impl;

import com.green.plate.greenplateapi.dto.OrderItemDTO;
import com.green.plate.greenplateapi.dto.PedidoDTO;
import com.green.plate.greenplateapi.exception.GreenPlateException;
import com.green.plate.greenplateapi.model.OrderItem;
import com.green.plate.greenplateapi.model.Pedido;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.repository.OrderItemRepository;
import com.green.plate.greenplateapi.repository.PedidoRepository;
import com.green.plate.greenplateapi.repository.UsuarioRepository;
import com.green.plate.greenplateapi.service.orderItem.impl.OrderItemServiceImpl;
import com.green.plate.greenplateapi.service.pedido.PedidoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final OrderItemServiceImpl orderItemService;
    private final UsuarioRepository usuarioRepository;
    private final OrderItemRepository orderItemRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, OrderItemServiceImpl orderItemService,
                             UsuarioRepository usuarioRepository,
                             OrderItemRepository orderItemRepository) {
        this.pedidoRepository = pedidoRepository;
        this.orderItemService = orderItemService;
        this.usuarioRepository = usuarioRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Transactional
    @Override
    public void savePedido(PedidoDTO pedidoDTO, Integer userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(GreenPlateException::new);
        pedidoDTO.setCustomerId(usuario.getCustomer().getId());
        Pedido newPedido = mapToPedido(pedidoDTO);
//        newPedido.setOrderItemList(new ArrayList<>());
        newPedido = pedidoRepository.saveAndFlush(newPedido);
        for (OrderItemDTO orderItemDTO : pedidoDTO.getOrderItemList()) {
            orderItemDTO.setPedidoId(newPedido.getId());
            orderItemService.saveOrderItem(orderItemDTO);
        }
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
