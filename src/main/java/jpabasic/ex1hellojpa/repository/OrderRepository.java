package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

//    // 검색기능
//    public List<Order> findAll(OrderSearch orderSearch){
//
//    }
}
