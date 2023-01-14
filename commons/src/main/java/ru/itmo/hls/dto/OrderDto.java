package ru.itmo.hls.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private String username;

    @NotBlank
    private String paymentId;

    @NotBlank
    private String deliveryInfo;

    @NotBlank
    private Long productsId;
}