package jpabasic.ex1hellojpa.OneToOne;


import jakarta.persistence.*;
import lombok.Getter;

//@Entity
@Getter
public class LockerMember {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    private String userName;
}
