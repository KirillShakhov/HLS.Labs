package ru.itmo.hps.lab1.core.payload;

import lombok.Data;
import ru.itmo.hps.lab1.core.entity.AttachmentType;

import javax.validation.constraints.NotBlank;


@Data
public class AttachmentRequest {
    @NotBlank
    private String base64;
    @NotBlank
    private AttachmentType type;
}