package com.green.plate.greenplateapi.service.usuario.impl;

import com.green.plate.greenplateapi.dto.ChangePasswordDTO;
import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.repository.UsuarioRepository;
import com.green.plate.greenplateapi.service.token.impl.TokenServiceImpl;
import com.green.plate.greenplateapi.service.usuario.UsuarioService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenServiceImpl tokenService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, TokenServiceImpl tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        usuarioDTO.setUserName(usuarioDTO.getUserName().toLowerCase());
        usuarioDTO.setPassword(DigestUtils.sha1Hex(usuarioDTO.getPassword()));
        Usuario usuario = mapUsuario(usuarioDTO);
        return mapToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> getUsuarioByUserName(String userName) {
        return usuarioRepository.findByUserName(userName.toLowerCase());
    }

    @Override
    public Optional<UsuarioDTO> checkUsuarioByUserName(String userName) {
        return this.getUsuarioByUserName(userName).map(this::mapToUsuarioDTO);
    }

    @Override
    public void login(Usuario usuario) {
        String refreshToken = tokenService.generateRefreshToken(usuario);
        usuario.setToken(tokenService.generateAccessToken(usuario));
        usuario.setRefreshToken(refreshToken);
        usuarioRepository.updateRefreshToken(usuario.getRefreshToken(), usuario.getId());
        usuario.setPassword(null);
    }

    @Override
    public void updatePassword(Usuario usuario, ChangePasswordDTO changePasswordDTO) {
        String newPass = DigestUtils.sha1Hex(changePasswordDTO.getNewPassword());
        usuarioRepository.updatePassword(newPass, usuario.getId());
    }

    @Override
    public Optional<Usuario> findByRefreshToken(String refToken) {
        return usuarioRepository.findByRefreshToken(refToken);
    }

    private Usuario mapUsuario(UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    private UsuarioDTO mapToUsuarioDTO(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
