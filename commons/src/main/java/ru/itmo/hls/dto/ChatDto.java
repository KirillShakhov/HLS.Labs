package ru.itmo.hls.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class ChatDto {
    private Long id;

    private String title;

    private String adminUser;

    private Set<String> users;
}
