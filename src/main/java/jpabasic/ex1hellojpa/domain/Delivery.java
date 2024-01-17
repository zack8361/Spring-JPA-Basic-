package jpabasic.ex1hellojpa.domain;


import jakarta.persistence.*;
import jpabasic.ex1hellojpa.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;
}
