package jpabasic.ex1hellojpa.repository;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.api.OrderDto;
import jpabasic.ex1hellojpa.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("SELECT o from Order o " +
                                "JOIN FETCH o.member " +
                                "JOIN FETCH o.delivery",
                        Order.class)
                .getResultList();
    }


    public List<OrderDto> findOrderDto() {
        return em.createQuery("SELECT o from Order o JOIN o.member m JOIN o.delivery d", OrderDto.class)
                .getResultList();
    }


    public List<Order> findAllWithItem() {
        return em.createQuery("SELECT DISTINCT o From Order o " +
                                "JOIN FETCH o.member m " +
                                "JOIN FETCH o.delivery d " +
                                "JOIN FETCH o.orderItems " +
                                "oi JOIN FETCH oi.item"
                        , Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return em.createQuery("SELECT o from Order o " +
                                "JOIN FETCH o.member " +
                                "JOIN FETCH o.delivery",
                        Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}
