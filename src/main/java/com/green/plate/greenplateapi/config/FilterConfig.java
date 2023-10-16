package com.green.plate.greenplateapi.config;

import com.green.plate.greenplateapi.service.token.TokenService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final TokenService tokenService;

    public FilterConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new JwtFilter(tokenService));
        filter.addUrlPatterns("/api/*");
        return filter;
    }
}
