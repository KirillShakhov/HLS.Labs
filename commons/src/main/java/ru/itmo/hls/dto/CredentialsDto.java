package ru.itmo.hls.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    @NotBlank(message = "Username cannot be empty")
    String username;

    @NotBlank(message = "Username cannot be empty")
    String password;

}
