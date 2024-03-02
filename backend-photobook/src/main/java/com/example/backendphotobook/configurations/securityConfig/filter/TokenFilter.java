package com.example.backendphotobook.configurations.securityConfig.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendphotobook.services.TokenService;
import com.example.backendphotobook.services.UsuariosService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtRequisicao = authHeader.substring(7);
        DecodedJWT jwtDecoded;

        try {
            jwtDecoded = tokenService.verifyToken(jwtRequisicao);

            if (jwtDecoded != null) {
                String userEmail = tokenService.getSubject(jwtDecoded);
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    try {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                        if (userDetails != null) {
                            if (tokenService.isTokenValid(jwtDecoded)) {
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                                authToken.setDetails(
                                        new WebAuthenticationDetailsSource().buildDetails(request)
                                );
                                SecurityContextHolder.getContext().setAuthentication(authToken);
                            } else {
                                response.sendError(HttpStatus.FORBIDDEN.value(), "Token expirado");
                            }
                        }
                    } catch (Exception e) {
                        response.sendError(HttpStatus.NOT_FOUND.value(), "Usuário não encontrado");
                    }
                }
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Token inválido");
            }
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Erro na validação do token.");
        }

        filterChain.doFilter(request, response);
    }
}
