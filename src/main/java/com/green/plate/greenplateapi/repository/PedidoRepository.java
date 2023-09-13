package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
