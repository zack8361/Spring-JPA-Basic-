package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.EntityEx.ItemList.Item;
import jpabasic.ex1hellojpa.EntityEx.Member;
import jpabasic.ex1hellojpa.EntityEx.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntityRepository {
    private final EntityManager em;

    public void saveMem(Member member){
        em.persist(member);
    }

    public void saveOrder(Order order) {
        em.persist(order);
    }

    public void saveItem(Item item) {

        em.persist(item);
    }
}
