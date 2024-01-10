package jpabasic.ex1hellojpa.EntityEx;


import jakarta.persistence.*;
import jpabasic.ex1hellojpa.enumc.DeliveryState;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    private String city;
    private String street;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;
    @OneToOne(mappedBy = "delivery")
    private Order order;
}
