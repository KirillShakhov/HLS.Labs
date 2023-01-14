package ru.itmo.hls.facade.security.jwt;

import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;


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

    public String extractUsername(@NonNull String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(@NonNull String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(@NonNull String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
