package ru.itmo.hls.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;


@Value
@Builder(toBuilder = true)
public class PageDto {

    @Singular
    List<Object> items;
    boolean hasMore;

}
