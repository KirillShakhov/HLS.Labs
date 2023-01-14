package ru.itmo.hls.facade.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.facade.client.fallback.AuthClientFallback;

import javax.validation.constraints.NotNull;

@ReactiveFeignClient(name = "auth-service")
public interface AuthClient {
    @PostMapping("/api/v1/auth/login")
    Mono<TokenDto> login(@NotNull CredentialsDto credentialsDto);
}
