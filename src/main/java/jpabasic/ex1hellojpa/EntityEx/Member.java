package jpabasic.ex1hellojpa.EntityEx;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Column(name = "member_id")
    @Id @GeneratedValue
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    // 기간 Period
    @Embedded
    private Period period;

    // 주소 Address
    @Embedded
    private Address address;
}
