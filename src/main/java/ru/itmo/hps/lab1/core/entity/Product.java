package ru.itmo.hps.lab1.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @JoinColumn(name="user_id", nullable=false)
    private String username;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", nullable=false)
    @ToString.Exclude
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "attachment_id")
    @ElementCollection(targetClass = Long.class, fetch = FetchType.LAZY)
    private List<Long> attachments;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
