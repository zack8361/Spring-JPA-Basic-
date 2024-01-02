package jpabasic.ex1hellojpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
public class Member {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
}
