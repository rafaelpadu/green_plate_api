package com.green.plate.greenplateapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Usuario}
 */
@Data
public class UsuarioDTO implements Serializable {
    Integer id;
    String userName;
    String email;
    String passWord;
    boolean active;
}
