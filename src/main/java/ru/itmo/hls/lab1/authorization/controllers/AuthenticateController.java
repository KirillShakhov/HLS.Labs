package ru.itmo.hls.lab1.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.lab1.authorization.security.UserPrincipal;
import ru.itmo.hls.lab1.authorization.services.RefreshTokenService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(refreshTokenService.validateToken(token));
    }

    @PostMapping("/login")
    public TokenDto login(@Valid @RequestBody CredentialsDto credentialsDto) {
        return refreshTokenService.newTokens(userPrincipal(credentialsDto));
    }

    @PostMapping("/token")
    public ResponseEntity<TokenDto> token(@Valid @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(refreshTokenService.newAccessToken(tokenDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@Valid @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(refreshTokenService.newRefreshToken(tokenDto));
    }

    private UserPrincipal userPrincipal(CredentialsDto credentialsDto) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(
                credentialsDto.getUsername(), credentialsDto.getPassword());
        final var authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (UserPrincipal) authentication.getPrincipal();
    }

}
