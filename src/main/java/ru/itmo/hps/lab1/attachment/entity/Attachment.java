package ru.itmo.hps.lab1.attachment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos", schema = "public")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "base64", nullable = false)
    private String base64;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private AttachmentType type;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private String createDate;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
