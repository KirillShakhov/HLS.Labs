package ru.itmo.hls.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private Long id;

    private String title;

    private String adminUser;

    private Set<String> users;
    private Set<MessageDto> messages;
}
