package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findaAll(){
        return em.createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }
}
