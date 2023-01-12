package ru.itmo.hls.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    @NotBlank(message = "Empty JWT Access Token is not allowed")
    String accessToken;

    @NotBlank(message = "Empty JWT Refresh Token is not allowed")
    String refreshToken;

}
