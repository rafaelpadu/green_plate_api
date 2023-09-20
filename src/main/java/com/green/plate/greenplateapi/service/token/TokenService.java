package com.green.plate.greenplateapi.service.token;

import com.green.plate.greenplateapi.model.Usuario;
import io.jsonwebtoken.Claims;

public interface TokenService {

    Claims decodeToken(String token);

    String generateAccessToken(Usuario usuario);

    String generateRefreshToken(Usuario user, String origin);
}
