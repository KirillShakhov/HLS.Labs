package ru.itmo.hls.lab1.authorization.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class JwtProperties {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expire-duration.access}")
    private long accessExpireDuration;

    @Value("${jwt.expire-duration.refresh}")
    private long refreshExpireDuration;

}
