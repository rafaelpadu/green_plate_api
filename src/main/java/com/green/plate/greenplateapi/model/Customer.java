package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Customer extends BaseEntityAudit {
    @Column
    private String fullName;
    @Column
    private String phone;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne
    private Usuario usuario;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
