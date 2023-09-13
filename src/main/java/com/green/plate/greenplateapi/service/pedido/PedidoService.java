package com.green.plate.greenplateapi.service.pedido;

import com.green.plate.greenplateapi.dto.PedidoDTO;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidoDTO savePedido(PedidoDTO pedidoDTO);

    List<PedidoDTO> getAllPedidos();

    Optional<PedidoDTO> getPedidoById(Integer id);
}
