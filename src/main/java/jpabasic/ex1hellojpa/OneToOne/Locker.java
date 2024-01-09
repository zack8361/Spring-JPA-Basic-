package jpabasic.ex1hellojpa.OneToOne;


import jakarta.persistence.*;
import lombok.Getter;

//@Entity
@Getter
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private LockerMember lockerMember;
}
