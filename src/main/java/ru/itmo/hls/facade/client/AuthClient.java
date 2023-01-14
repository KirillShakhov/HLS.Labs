package ru.itmo.hls.facade.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;

import javax.validation.constraints.NotNull;

@FeignClient("auth-service")
public interface AuthClient {
    @PostMapping("/api/v1/auth/login")
    TokenDto login(@NotNull CredentialsDto credentialsDto);
}
