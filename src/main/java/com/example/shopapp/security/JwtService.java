package com.example.shopapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {
    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String username) { // принимает логин // генерация токена
        Date date = Date.from(LocalDateTime.now().plusDays(10).atZone(ZoneId.systemDefault()).toInstant()); // указываем, сколько наш токен будет валиден
        return Jwts.builder()
                .subject(username)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getSignInKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            LOGGER.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            LOGGER.error("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            LOGGER.error("Malformed jwt");
        } catch (SecurityException sEx) {
            LOGGER.error("Invalid signature");
        } catch (Exception e) {
            LOGGER.error("invalid token");
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}