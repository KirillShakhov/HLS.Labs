package ru.itmo.hps.lab1.core.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Order.class)
    private Long id;

    @NotNull
    @JoinColumn(name="user_id", nullable=false)
    @JsonView(View.Order.class)
    private String username;

    @NotNull
    @JoinColumn(name="payment_id", nullable=false)
    @JsonView(View.Order.class)
    private String paymentId;

    @Column(name = "delivery_info")
    @JsonView(View.Order.class)
    private String deliveryInfo;

    @ElementCollection
    @CollectionTable(name="order_products")
    @MapKeyJoinColumn(name="product_id")
    @Column(name="count")
    @JsonView(View.Order.class)
    private Map<Product, Integer> products = new HashMap<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
