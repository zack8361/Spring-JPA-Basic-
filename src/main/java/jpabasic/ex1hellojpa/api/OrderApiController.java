package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.OrderItem;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;


/**
 * One To Many 상황
 */
@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;
    

    @GetMapping("/api/v2/orders")
    public List<OrderDto2> ordersV2(){

        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto2::new)
                .collect(toList());
    }


    @Data
    static class OrderDto2{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;
        public OrderDto2(Order o) {
            this.orderId = o.getId();
            this.name = o.getMember().getUserName();
            this.orderDate = o.getOrderDate();
            this.orderStatus = o.getOrderStatus();
            this.address = o.getDelivery().getAddress();
            this.orderItems = o.getOrderItems().stream()
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
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

}
