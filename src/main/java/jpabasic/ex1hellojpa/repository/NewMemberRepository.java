package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.Entity.NewMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NewMemberRepository {

    private final EntityManager em;
    public void save(NewMember newMember) {
        em.persist(newMember);
    }
}
