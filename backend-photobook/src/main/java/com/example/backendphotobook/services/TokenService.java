package com.example.backendphotobook.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendphotobook.entities.UsuariosEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "secret_key";
    private static final Long EXPIRATION_MILLIS = 10_000L;

    public String generateToken(UsuariosEntity usuariosEntity, String emissor) {

        return JWT.create()
                .withIssuer("back-end")
                .withSubject(usuariosEntity.getEmail())
                .withIssuedAt(LocalDateTime.now()
                        .toInstant(ZoneOffset.of("-03:00")))
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(60)
                        .toInstant(ZoneOffset.of("-03:00")))
                .withClaim("id", usuariosEntity.getUsuarioId())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getSubject(DecodedJWT token) {
        return token.getSubject();
    }

    public DecodedJWT verifyToken(String jwt) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build().verify(jwt);
    }

    public boolean isTokenValid(DecodedJWT jwtDecoded) {
        Date expirationDate = jwtDecoded.getExpiresAt();
        Date currentDate = new Date();
        return currentDate.before(expirationDate);
    }
}
