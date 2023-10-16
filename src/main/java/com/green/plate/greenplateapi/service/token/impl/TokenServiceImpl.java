package com.green.plate.greenplateapi.service.token.impl;

import com.green.plate.greenplateapi.model.Usuario;
import com.green.plate.greenplateapi.service.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${authentication.jwtToken.secret}")
    private String secretString;

    private Key secret;

    @PostConstruct
    public void init(){
        byte[] secretBytes = secretString.getBytes(StandardCharsets.UTF_8);
        this.secret = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    public Claims decodeToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.secret).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String generateAccessToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUserName())
                .claim("userId", usuario.getId())
                .claim("username", usuario.getUserName())
                .setExpiration(getExpirationDate())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate())
                .signWith(secret, SignatureAlgorithm.HS256).compact();
    }

    @Override
    public String generateRefreshToken(Usuario user) {
        return Jwts.builder().setSubject(user.getUserName())
                .claim("userId", user.getId())
                .claim("username", user.getUserName())
                .setIssuedAt(org.joda.time.LocalDate.now().toDate()).signWith(secret, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isPasswordValid(Usuario usuario, String password) {
        String passwordHash = DigestUtils.sha1Hex(password);
        return passwordHash.equals(usuario.getPassword());
    }

    @NotNull
    private Date getExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + 180000);
    }
}
