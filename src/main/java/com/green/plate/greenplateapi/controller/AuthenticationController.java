package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.service.token.impl.TokenServiceImpl;
import com.green.plate.greenplateapi.service.usuario.UsuarioService;
import com.green.plate.greenplateapi.service.usuario.impl.UsuarioServiceImpl;
import com.green.plate.greenplateapi.utils.JsonStruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UsuarioService usuarioService;
    private final TokenServiceImpl tokenService;

    public AuthenticationController(UsuarioService usuarioService, TokenServiceImpl tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> login(@RequestBody UsuarioDTO usuarioDTO) {
        JsonStruct jsonStruct = new JsonStruct();
        if (usuarioDTO.getUserName() == null || usuarioDTO.getPassword() == null) {
            jsonStruct.setMessage("Por favor digite o email e senha corretamente");
            return new ResponseEntity<>(jsonStruct, HttpStatus.UNAUTHORIZED);
        }
        Optional<Usuario> usuario = usuarioService.getUsuarioByUserName(usuarioDTO.getUserName());
        if (usuario.isEmpty()) {
            jsonStruct.setMessage("Usuário não encontrado");
            return new ResponseEntity<>(jsonStruct, HttpStatus.NOT_FOUND);
        }
        if (!tokenService.isPasswordValid(usuario.get(), usuarioDTO.getPassword())) {
            jsonStruct.setMessage("Senha incorreta");
            return new ResponseEntity<>(jsonStruct, HttpStatus.UNAUTHORIZED);
        }
        usuarioService.login(usuario.get());

        jsonStruct.put("token", usuario.get().getToken());
        jsonStruct.put("refreshToken", usuario.get().getRefreshToken());
        return new ResponseEntity<>(jsonStruct, HttpStatus.OK);
    }

    @GetMapping(value = "/refresh")
    public ResponseEntity<JsonStruct> refreshToken(@RequestParam("refreshToken") String refToken){
        JsonStruct struct = new JsonStruct();
        Optional<Usuario> usuario = usuarioService.findByRefreshToken(refToken);
        if(usuario.isEmpty()){
            struct.setMessage("Usuário não encontrado. Faça login novamente");
            return new ResponseEntity<>(struct, HttpStatus.UNAUTHORIZED);
        }
        usuarioService.login(usuario.get());

        struct.put("token", usuario.get().getToken());
        struct.put("refreshToken", usuario.get().getRefreshToken());
        return new ResponseEntity<>(struct, HttpStatus.OK);
    }
}
