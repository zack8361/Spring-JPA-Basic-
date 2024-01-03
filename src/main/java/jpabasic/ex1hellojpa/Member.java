package jpabasic.ex1hellojpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue
    @Column(name = "user_id",nullable = false, length = 10)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 3)
    private String name;

    @Column(name = "user_age", nullable = false, length = 3)
    private int age;
}
