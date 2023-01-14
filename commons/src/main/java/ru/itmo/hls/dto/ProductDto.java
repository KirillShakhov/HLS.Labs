package ru.itmo.hls.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String category;
    @NotBlank
    private String description;
    @NotBlank
    @ToString.Exclude
    private List<Long> attachment;
}