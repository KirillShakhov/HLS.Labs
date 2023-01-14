package ru.itmo.hls.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PageDto {
    @Singular
    List<Object> items;
    boolean hasMore;

}
