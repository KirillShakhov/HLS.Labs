package ru.itmo.hls.facade.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@FeignClient("auth-service")
public interface AuthClient {
    @PostMapping("/api/v1/auth/login")
    TokenDto login(@NotNull CredentialsDto credentialsDto);

    @PostMapping("/api/v1/users/add")
    String registerNewUser(@Valid @RequestBody UserDto userDto);
}
