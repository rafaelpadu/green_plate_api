package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario extends BaseEntityAudit {
    @Column
    private String userName;
    @NotBlank(message = "E-mail é obrigatorio")
    @Email(message = "E-mail não é válido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Senha é obrigatorio")
    @Length(min = 6, message = "A senha tem mínimo de 6 caracteres")
    private String passWord;
    @OneToOne
    private Customer customer;
    @Column
    private boolean active = true;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
