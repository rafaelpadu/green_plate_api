package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByRefreshToken(String refreshToken);
    Optional<Usuario> findByUserName(String userName);
    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.refreshToken = ?1  where u.id = ?2")
    void updateRefreshToken(String refreshToken, Integer id);
    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.password = ?1 where u.id = ?2")
    void updatePassword(String newPass, Integer id);
}
