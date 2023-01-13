package ru.itmo.hls.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.hls.entity.AttachmentType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    private Long id;

    private String base64;

    private AttachmentType type;

    private String createDate;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
