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
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JoinColumn(name="user_id", nullable=false)
    private String username;

    @NotNull
    @JoinColumn(name="payment_id", nullable=false)
    private String paymentId;

    @Column(name = "delivery_info")
    private String deliveryInfo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id", nullable=false)
    @ToString.Exclude
    private Product products;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
