package ru.itmo.hls.lab1.authorization.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.exception.RefreshTokenException;
import ru.itmo.hls.lab1.authorization.security.UserPrincipal;
import ru.itmo.hls.lab1.authorization.security.UserPrincipalService;
import ru.itmo.hls.lab1.authorization.security.jwt.JwtTokenProvider;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserPrincipalService userPrincipalService;

    private final JwtTokenProvider jwtTokenProvider;

    private UserPrincipal userPrincipal(@NonNull TokenDto tokenDto) {
        if (!jwtTokenProvider.validateToken(tokenDto.getRefreshToken())) {
            throw new RefreshTokenException("Invalid or expired refresh token");
        }
        return (UserPrincipal) userPrincipalService.loadUserById(
                jwtTokenProvider.extractId(tokenDto.getRefreshToken()));
    }

    public TokenDto newTokens(@NonNull UserPrincipal userPrincipal) {
        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(userPrincipal))
                .refreshToken(jwtTokenProvider.generateRefreshToken(userPrincipal))
                .build();
    }

    public TokenDto newAccessToken(@NonNull TokenDto tokenDto) {
        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(userPrincipal(tokenDto)))
                .refreshToken(tokenDto.getRefreshToken())
                .build();
    }

    public TokenDto newRefreshToken(@NonNull TokenDto tokenDto) {
        return TokenDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(jwtTokenProvider.generateRefreshToken(userPrincipal(tokenDto)))
                .build();
    }

    public boolean validateToken(@NonNull String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
