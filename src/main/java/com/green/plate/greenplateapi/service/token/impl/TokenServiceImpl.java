package com.green.plate.greenplateapi.service.token.impl;

import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.service.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    private Key secret;
    @Override
    public Claims decodeToken(String token){
        return Jwts.parserBuilder().setSigningKey(this.secret).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String generateAccessToken(Usuario usuario){
        return Jwts.builder()
                .setSubject(usuario.getUserName())
                .claim("userId", usuario.getId())
                .claim("username", usuario.getUserName())
                .setExpiration(getExpirationDate())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate())
                .signWith(secret, SignatureAlgorithm.HS256).compact();
    }
    @Override
    public String generateRefreshToken(Usuario user, String origin) {
        return Jwts.builder().setSubject(user.getUserName())
                .claim("userId", user.getId())
                .claim("client", origin)
                .claim("username", user.getUserName())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate()).signWith(secret, SignatureAlgorithm.HS256)
                .compact();
    }
    @NotNull
    private Date getExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + 180000);
    }
}
