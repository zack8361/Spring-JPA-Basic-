package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.Entity.Member;
import jpabasic.ex1hellojpa.Entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExampleRepository {

    private final EntityManager entityManager;


    public void saveMem(Member member){
        entityManager.persist(member);
    }

    public void saveTeam(Team team){
        entityManager.persist(team);
    }
}
