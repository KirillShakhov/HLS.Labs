package ru.itmo.hls.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@RequiredArgsConstructor
public class CredentialsDto {

    @NotBlank(message = "Username cannot be empty")
    String username;

    @NotBlank(message = "Username cannot be empty")
    String password;

}
