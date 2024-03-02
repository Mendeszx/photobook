package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.LoginRequest;
import com.example.backendphotobook.dtos.response.LoginResponse;
import com.example.backendphotobook.entities.UsuariosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        LoginResponse response;
        try {
            UsernamePasswordAuthenticationToken dataLogin = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());
            Authentication authenticate = this.authenticationManager.authenticate(dataLogin);
            UsuariosEntity usuario = (UsuariosEntity) authenticate.getPrincipal();
            String token = tokenService.generateToken(usuario, "login");
            response = loginResponse(200, HttpStatus.OK, token, "Login realizado com sucesso.");

        } catch (AuthenticationException e) {
            response = loginResponse(400, HttpStatus.BAD_REQUEST, "", "Erro: " + e.getMessage());
        }
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    private LoginResponse loginResponse(int code, HttpStatus httpStatus, String token, String mensagem) {
        LoginResponse response = new LoginResponse();

        response.setHttpStatusCode(code);
        response.setHttpStatus(httpStatus);
        response.setJwt(token);
        response.setMensagem(mensagem);

        return response;
    }
}
