package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
