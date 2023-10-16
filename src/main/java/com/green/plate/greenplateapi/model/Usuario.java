package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends BaseEntityAudit {
    @Column(nullable = false, unique = true)
    private String userName;
    @NotBlank(message = "E-mail é obrigatorio")
    @Email(message = "E-mail não é válido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NotBlank(message = "Senha é obrigatorio")
    @Length(min = 6, message = "A senha tem mínimo de 6 caracteres")
    private String password;
    @OneToOne
    private Customer customer;
    @Column
    private boolean active = true;
    @Column
    private String refreshToken;
    @Transient
    private String token;

}
