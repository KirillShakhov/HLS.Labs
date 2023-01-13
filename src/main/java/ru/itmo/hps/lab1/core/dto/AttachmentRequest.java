package ru.itmo.hps.lab1.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AttachmentRequest {
    @NotBlank
    private String base64;
    @NotBlank
    private AttachmentType type;
}