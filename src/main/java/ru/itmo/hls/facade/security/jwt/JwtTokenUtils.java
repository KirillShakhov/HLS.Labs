package ru.itmo.hls.facade.security.jwt;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Component
public class JwtTokenUtils {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public String tokenFromRequest(@NonNull HttpServletRequest request) {
        final var header = request.getHeader(AUTHORIZATION);

        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

}
