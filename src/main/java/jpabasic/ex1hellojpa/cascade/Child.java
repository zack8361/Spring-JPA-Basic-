package jpabasic.ex1hellojpa.cascade;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter
@Setter
public class Child {

    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
    
    
    // 연관관계 편의 메서드
    public void addParent(Parent parent){
        this.parent = parent;
        parent.getChildList().add(this);
    }
}
