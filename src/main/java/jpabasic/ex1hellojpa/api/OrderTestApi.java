package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.OrderItem;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;


/**
 * ManyToOne뽑기
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class OrderTestApi {

    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<OrderDto3> orders(){

        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto3::new)
                .collect(toList());
    }


    @Data
    static class OrderDto3{
        private Long orderId;
        private String name;
        private Address address;
        private OrderStatus orderStatus;
        private LocalDateTime orderDate;
        private List<OrderItemDto> orderItems;
        public OrderDto3(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getUserName();
            this.address = order.getDelivery().getAddress();
            this.orderStatus = order.getOrderStatus();
            this.orderDate = order.getOrderDate();
            this.orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }
    }
    @Data
    static class OrderItemDto{
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
