package com.green.plate.greenplateapi.config;

import com.green.plate.greenplateapi.service.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.regex.Pattern;

public class JwtFilter extends GenericFilterBean {
    private final TokenService tokenService;

    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private static Pattern pattern = Pattern.compile(".*/secure/.*");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            final String authHeader = request.getHeader("authorization");
            if (pattern.matcher(request.getRequestURI()).matches()) {
                if ("OPTIONS".equals(request.getMethod())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    filterChain.doFilter(request, response);
                } else {
                    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        throw new ServletException();
                    }
                }
                final String token = authHeader.substring(7);
                Claims claims = tokenService.decodeToken(token);
                request.setAttribute("claims", claims);
            }
            filterChain.doFilter(request, response);
        } catch (ServletException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
        } catch (ExpiredJwtException expiredJwtException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
        }

    }
}
