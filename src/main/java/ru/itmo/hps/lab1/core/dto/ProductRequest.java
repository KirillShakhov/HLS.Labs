package ru.itmo.hps.lab1.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Long category;
    @NotBlank
    private String description;
    @NotBlank
    private List<Long> attachment;
}