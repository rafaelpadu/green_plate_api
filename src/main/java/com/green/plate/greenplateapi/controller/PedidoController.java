package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.PedidoDTO;
import com.green.plate.greenplateapi.service.pedido.impl.PedidoServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoServiceImpl pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/new-pedido/{userId}")
    public ResponseEntity<PedidoDTO> newPedido(@RequestBody @Valid PedidoDTO pedidoDTO, @PathVariable("userId") Integer userId) {
        try {
            pedidoService.savePedido(pedidoDTO, userId);
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(PedidoController.class);
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }

    @GetMapping("/")
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Integer id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
