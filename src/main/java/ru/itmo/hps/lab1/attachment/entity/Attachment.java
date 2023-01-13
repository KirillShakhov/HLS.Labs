package ru.itmo.hps.lab1.attachment.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;


@Data
@Builder
@Table(name = "attachment", schema = "public")
public class Attachment {
    @Id
    @GeneratedValue
    @Column("id")
    private Long id;

    @NotNull
    @Column("base64")
    private String base64;

    @NotNull
    @Column("type")
    @ToString.Exclude
    private AttachmentType type;

    @NotNull
    @Column("create_date")
    private String createDate;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
