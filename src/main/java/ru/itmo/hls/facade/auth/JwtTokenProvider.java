package ru.itmo.hls.facade.auth;

import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public boolean validateToken(@NonNull String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (SignatureException exception) {
            log.error("Invalid JWT signature: {}", exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT token: {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("JWT token is expired: {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("JWT token is unsupported: {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims string is empty: {}", exception.getMessage());
        }
        return false;
    }

    public void extractAllClaims(@NonNull String token) {
        Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
