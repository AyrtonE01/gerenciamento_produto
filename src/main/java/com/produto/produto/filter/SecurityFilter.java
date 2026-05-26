package com.produto.produto.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/produtos").permitAll()
                                // essas são as rotas publicas (que são permitidas sem a autenticação)
                                .requestMatchers(HttpMethod.POST, "/produtos").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/produtos/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                                // já essa é a outra rota (que inclui GET /usuarios) no qual exigira a autenticação
                                .anyRequest().authenticated()
                )
                .build();
    }
}