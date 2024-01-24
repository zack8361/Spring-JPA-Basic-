package jpabasic.ex1hellojpa.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @NotEmpty(message = "값이 비어있을수 없습니다.")
    private String userName;

    @Embedded
    private Address address;
}
