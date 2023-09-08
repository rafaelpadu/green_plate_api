package com.green.plate.greenplateapi.service.usuario;

import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void save(UsuarioDTO usuarioDTO);

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuarioById(Integer id);
}
