package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 * XToONE(ManyToOne, OneToOne)
 * Order 조회
 * Order -> member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersv2(){
        List<Order> orders = orderRepository.findAll();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(toList());
        return result;
    }

    @GetMapping("api/v3/simple-orders")
    public List<OrderDto> orderV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }


    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            this.orderId = order.getId();
            this.name = order.getMember().getUserName();
            this.orderDate = order.getOrderDate();
            this.address = order.getMember().getAddress();
        }
    }
}
