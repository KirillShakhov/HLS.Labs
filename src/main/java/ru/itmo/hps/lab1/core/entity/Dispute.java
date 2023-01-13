package ru.itmo.hps.lab1.core.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "disputes", schema = "public")
public class Dispute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Dispute.class)
    private Long id;

    @NotNull
    @JoinColumn(name="user_id", nullable=false)
    @JsonView(View.Dispute.class)
    private String username;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    @JsonView(View.Dispute.class)
    @ToString.Exclude
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    @JsonView(View.Dispute.class)
    @ToString.Exclude
    private Product product;

    @Column(name = "is_clothed", nullable = false)
    @JsonView(View.Dispute.class)
    private Boolean isClothed = false;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
