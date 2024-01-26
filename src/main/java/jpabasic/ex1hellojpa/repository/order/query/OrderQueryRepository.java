package jpabasic.ex1hellojpa.repository.order.query;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {

        List<OrderQueryDto> resultList = em.createQuery("SELECT new jpabasic.ex1hellojpa.repository.order.query.OrderQueryDto(o.id,m.userName,o.orderDate,o.orderStatus,d.address)" +
                        " from Order o " +
                        "JOIN o.member m " +
                        "JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
        return resultList;
    }
}
