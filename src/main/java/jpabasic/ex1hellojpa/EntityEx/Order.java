package jpabasic.ex1hellojpa.EntityEx;


import jakarta.persistence.*;
import jpabasic.ex1hellojpa.enumc.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Column(name = "order_id", nullable = false)
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
