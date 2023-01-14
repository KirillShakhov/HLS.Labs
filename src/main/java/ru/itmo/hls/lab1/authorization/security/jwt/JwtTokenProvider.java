package ru.itmo.hls.lab1.authorization.security.jwt;

import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.itmo.hls.lab1.authorization.security.UserPrincipal;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";

    private final JwtProperties jwtProperties;

    /**
     * Create a new JWT Access token.
     */
    public String generateAccessToken(@NonNull UserPrincipal userPrincipal) {
        return generateToken(userPrincipal, jwtProperties.getAccessExpireDuration());
    }

    /**
     * Create a new JWT Refresh Token.
     */
    public String generateRefreshToken(@NonNull UserPrincipal userPrincipal) {
        return generateToken(userPrincipal, jwtProperties.getRefreshExpireDuration());
    }

    /**
     * Create a new JWT Token by user details.
     */
    private String generateToken(@NonNull UserPrincipal userPrincipal, long expireDuration) {
        String authorities = userPrincipal.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.joining(","));
        return Jwts.builder()
                .setId(userPrincipal.getId().toString())
                .setSubject(userPrincipal.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expireDuration)))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

    /**
     * Check if a JWT Token is expired.
     */
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

    /**
     * Extract all claims of a JWT Token.
     */
    public Claims extractAllClaims(@NonNull String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token).getBody();
    }

    /**
     * Extract claim by function from a JWT Token.
     */
    public <T> T extractClaim(@NonNull String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(extractAllClaims(token));
    }

    /**
     * Extract id from a JWT Token.
     */
    public UUID extractId(@NonNull String token) {
        return UUID.fromString(extractClaim(token, Claims::getId));
    }

    /**
     * Extract username from a JWT Token.
     */
    public String extractUsername(@NonNull String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration day from a JWT Token.
     */
    public Date extractExpireDate(@NonNull String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
