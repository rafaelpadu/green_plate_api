package com.green.plate.greenplateapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.plate.greenplateapi.dto.UsuarioDTO;
import com.green.plate.greenplateapi.service.usuario.UsuarioService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class UsuarioControllerTeste {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioDTO usuario;

    @BeforeEach
    public void setUsuario() {
        usuario = UsuarioDTO.builder()
                .userName("Teste da Silva")
                .email("teste@teste.com")
                .passWord("123456")
                .build();
    }

    @Test
    public void givenUsuario_whenCreateUsuario_thenReturnSavedUsuario() throws  Exception{
        given(usuarioService.save(ArgumentMatchers.any(UsuarioDTO.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(usuario)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(usuario.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.passWord", CoreMatchers.is(usuario.getPassWord())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", CoreMatchers.is(usuario.getCustomerId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", CoreMatchers.is(usuario.getUserName())));
    }
}
