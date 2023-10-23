package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.ChangePasswordDTO;
import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.service.token.impl.TokenServiceImpl;
import com.green.plate.greenplateapi.service.usuario.impl.UsuarioServiceImpl;
import com.green.plate.greenplateapi.utils.JsonStruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UsuarioServiceImpl usuarioService;
    private final TokenServiceImpl tokenService;

    public UserController(UsuarioServiceImpl usuarioService, TokenServiceImpl tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
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

    @PutMapping("/secure/new-password")
    public ResponseEntity<JsonStruct> createNewPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        JsonStruct jsonStruct = new JsonStruct();

        Optional<Usuario> usuariOpt = usuarioService.getUsuarioById(changePasswordDTO.getUserId());
        if(usuariOpt.isEmpty()){
            jsonStruct.setMessage("Usuário não encontrado");
            return new ResponseEntity<>(jsonStruct, HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuariOpt.get();
        if(!tokenService.isPasswordValid(usuario, changePasswordDTO.getOldPassword())){
            jsonStruct.setMessage("Senha antiga incorreta");
            return new ResponseEntity<>(jsonStruct, HttpStatus.UNAUTHORIZED);
        }
        usuarioService.updatePassword(usuario, changePasswordDTO);
        jsonStruct.setMessage("Senha alterada com sucesso");
        return new ResponseEntity<>(jsonStruct, HttpStatus.OK);
    }
    @PutMapping("/reset-password")
    public ResponseEntity<JsonStruct> resetPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        JsonStruct jsonStruct = new JsonStruct();

        Optional<Usuario> usuariOpt = usuarioService.getUsuarioById(changePasswordDTO.getUserId());
        if(usuariOpt.isEmpty()){
            jsonStruct.setMessage("Usuário não encontrado");
            return new ResponseEntity<>(jsonStruct, HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuariOpt.get();
        usuarioService.updatePassword(usuario, changePasswordDTO);
        jsonStruct.setMessage("Senha alterada com sucesso");
        return new ResponseEntity<>(jsonStruct, HttpStatus.OK);
    }

    @GetMapping("/check-username/{userName}")
    public ResponseEntity<UsuarioDTO> checkIfUsuarioExists(@PathVariable String userName){
        return usuarioService
                .checkUsuarioByUserName(userName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
