package jpabasic.ex1hellojpa.EntityEx.ItemList;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Album extends Item{

    private String artist;
    private String etc;
}
