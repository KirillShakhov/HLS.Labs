package ru.itmo.hps.lab1.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class CategoryRequest {
    @NotBlank
    @Size(min = 3, max = 40)
    private String name;
    @NotBlank
    @Size(min = 3, max = 25)
    private String shortName;
    @NotBlank
    private List<String> tags;
}