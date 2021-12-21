package com.israelgda.sistemacomprasapi.config;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Environment environment;
    private final JwtTokenStore tokenStore;

    public ResourceServerConfig(Environment environment, JwtTokenStore tokenStore) {
        this.environment = environment;
        this.tokenStore = tokenStore;
    }

    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
    private static final String[] USER_ROUTES = {"/carrinho/**", "/pedidos/**"};
    private static final String[] STORE_ROUTES = {"/produtos/**", "/categorias/**"};
    private static final String[] ADMIN_ROUTES = {"/produtos/**", "/categorias/**", "/descontos/**", "/cupoms/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.csrf().disable();
            http.headers().frameOptions().sameOrigin();
        }

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, STORE_ROUTES).hasAnyRole("USUARIO", "ADMIN")
                .antMatchers(USER_ROUTES).hasAnyRole("USUARIO", "ADMIN")
                .antMatchers(ADMIN_ROUTES).hasAnyRole("ADMIN")
                .anyRequest().authenticated();

        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
