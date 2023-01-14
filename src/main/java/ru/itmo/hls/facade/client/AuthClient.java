package ru.itmo.hls.facade.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@FeignClient("auth-service")
public interface AuthClient {
    @PostMapping("/api/v1/auth/validateToken")
    Boolean validateToken(@RequestParam String token);

    @PostMapping("/api/v1/auth/token")
    TokenDto token(@Valid @RequestBody TokenDto tokenDto);

    @PostMapping("/api/v1/auth/refresh")
    TokenDto refresh(@Valid @RequestBody TokenDto tokenDto);


    @PostMapping("/api/v1/auth/login")
    TokenDto login(@NotNull CredentialsDto credentialsDto);

    @PostMapping("/api/v1/users/add")
    String registerNewUser(@Valid @RequestBody UserDto userDto);

    @PostMapping("/api/v1/users/change-role")
    String changeRole(@RequestParam String username);

    @GetMapping("/api/v1/users/page/{number}")
    PageDto getAllUsers(@PathVariable Integer number);
}
