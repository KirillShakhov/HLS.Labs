package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.dto.UserDto;
import ru.itmo.hls.facade.client.AuthClient;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthClient authClient;

    @PostMapping("/login")
    public TokenDto login(@Valid @RequestBody CredentialsDto credentialsDto) {
        return authClient.login(credentialsDto);
    }

    @PostMapping("/validateToken")
    Boolean validateToken(@RequestParam String token){
        return authClient.validateToken(token);
    }

    @PostMapping("/token")
    TokenDto token(@Valid @RequestBody TokenDto tokenDto){
        return authClient.token(tokenDto);
    }

    @PostMapping("/refresh")
    TokenDto refresh(@Valid @RequestBody TokenDto tokenDto){
        return authClient.refresh(tokenDto);
    }
}
