package com.example.backendphotobook.configurations.securityConfig;

import com.example.backendphotobook.configurations.securityConfig.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    TokenFilter tokenFilter;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/v1/auth/login")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/cadastro/usuario")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/health/check")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/publicacao/nova-publicacao")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/publicacao/listar-publicacoes")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/publicacao/{publicacaoId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/v1/publicacao/deletar-publicacao")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/comentario/novo-comentario")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/comentario/listar-comentarios")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/v1/comentario/deletar-comentario")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/album/novo-album")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/album/listar-albuns/{usuarioId}")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
