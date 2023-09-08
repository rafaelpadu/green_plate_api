package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.service.usuario.impl.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UsuarioServiceImpl usuarioService;

    public UserController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<Usuario> getAllUsuario() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioByID(@PathVariable Integer id) {
        return usuarioService
                .getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
