package ru.itmo.hps.lab1.core.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @JsonView(View.Product.class)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    @JsonView(View.Product.class)
    private String name;

    @NotNull
    @JoinColumn(name="user_id", nullable=false)
    @JsonView(View.Product.class)
    private String username;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    @JsonView(View.Product.class)
    @ToString.Exclude
    private Category category;

    @Column(name = "description")
    @JsonView(View.Product.class)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_attachments",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    @ToString.Exclude
    private Set<Attachment> attachments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_comments",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @ToString.Exclude
    Set<Message> comments;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
