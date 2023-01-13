package ru.itmo.hls.facade.client.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.facade.client.AuthClient;

@Component
public class AuthClientFallback implements AuthClient {
    @Override
    public Mono<TokenDto> login(CredentialsDto credentialsDto) {
        return Mono.empty();
    }
}
