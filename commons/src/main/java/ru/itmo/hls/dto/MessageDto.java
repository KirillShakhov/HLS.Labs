package ru.itmo.hls.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MessageDto {
    private Long id;

    private String username;

    private String message;

    private String date;
}
