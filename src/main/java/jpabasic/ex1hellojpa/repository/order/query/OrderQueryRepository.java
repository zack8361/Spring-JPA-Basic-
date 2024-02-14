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

        // orders 에 있는 orderitems 루프를 돌면서 채워준다
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
        return em.createQuery("SELECT new jpabasic.ex1hellojpa.repository.order.query.OrderQueryDto(o.id,m.userName,o.orderDate,o.orderStatus,d.address) " +
                        "from Order o " +
                        "JOIN o.member m " +
                        "JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();


        // 여러개의 아이디를 한번에 뽑고 Query 문의 In 절에 넣는다.
        List<Long> orderIds = result.stream()
                .map(OrderQueryDto::getOrderId)
                .toList();


        List<OrderItemQueryDto> orderItems = em.createQuery("SELECT " +
                        "new jpabasic.ex1hellojpa.repository.order.query.OrderItemQueryDto(oi.order.id, i.name,oi.orderPrice,oi.count) " +
                        "FROM OrderItem oi " +
                        "JOIN oi.item i " +
                        "WHERE oi.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }


    public List<OrderFlatDto> findAllByDto_flat() {
        em.createQuery("SELECT new jpabasic.ex1hellojpa.repository.order.query.OrderFlatDto(o.id, m.userName, o.orderDate, o.orderStatus, d.address, i.name, oi.orderPrice, oi.count) from Order o " +
                        "JOIN o.member m " +
                        "JOIN o.delivery d " +
                        "JOIN o.orderItems oi " +
                        "JOIN oi.item i", OrderFlatDto.class)
                .getResultList();
        return null;
    }
}
