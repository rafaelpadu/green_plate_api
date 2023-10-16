package com.green.plate.greenplateapi.service.usuario;

import com.green.plate.greenplateapi.dto.NewPasswordDTO;
import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO save(UsuarioDTO usuarioDTO);

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuarioById(Integer id);

    Optional<Usuario> getUsuarioByUserName(String userName);

    void login(Usuario usuario);

    void updatePassword(Usuario usuario, NewPasswordDTO newPasswordDTO);

    Optional<Usuario> findByRefreshToken(String refToken);
}
