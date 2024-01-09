package jpabasic.ex1hellojpa.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {

    @Id  @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "team")
//    List<Member> members = new ArrayList<>();

    
    //  연관관계 편의 메서드
}
