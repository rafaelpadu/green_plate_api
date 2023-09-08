package com.green.plate.greenplateapi.service.usuario.impl;

import com.green.plate.greenplateapi.dto.CustomerDTO;
import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Customer;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.repository.UsuarioRepository;
import com.green.plate.greenplateapi.service.usuario.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    private Usuario mapUsuario(UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
}
