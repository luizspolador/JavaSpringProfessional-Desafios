package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()); // api rest n guarda estado em sessão, por isso foi desabilitado
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // configura a permissão para os endpoints
        return http.build();
    }
}
