package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /**
     * em.persist => 영속상태로 변경
     * @param member
     */
    
//  저장 
    public void save(Member member){
        em.persist(member);
    }

//  조회
    public Member findById(Long id) {
        return em.find(Member.class,id);
    }

//  전체 조회 with JPQL
    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        return result;
    }
}
