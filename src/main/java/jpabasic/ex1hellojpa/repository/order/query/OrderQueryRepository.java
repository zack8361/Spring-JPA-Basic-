package jpabasic.ex1hellojpa.repository.order.query;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery("SELECT " +
                        "new jpabasic.ex1hellojpa.repository.order.query.OrderItemQueryDto(oi.order.id, i.name,oi.orderPrice,oi.count) " +
                        "FROM OrderItem oi " +
                        "JOIN oi.item i " +
                        "WHERE oi.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
       return em.createQuery( "SELECT new jpabasic.ex1hellojpa.repository.order.query.OrderQueryDto(o.id,m.userName,o.orderDate,o.orderStatus,d.address) " +
                        "from Order o " +
                        "JOIN o.member m " +
                        "JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        List<Long> orderIds = result.stream()
                .map(OrderQueryDto::getOrderId)
                .toList();

        List<OrderItemQueryDto> orderItems = em.createQuery("SELECT " +
                        "new jpabasic.ex1hellojpa.repository.order.query.OrderItemQueryDto(oi.order.id, i.name,oi.orderPrice,oi.count) " +
                        "FROM OrderItem oi " +
                        "JOIN oi.item i " +
                        "WHERE oi.id = :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> collect = orderItems.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

        result.forEach(o-> o.setOrderItems(collect.get(o.getOrderId())));
        return result;
    }
}
