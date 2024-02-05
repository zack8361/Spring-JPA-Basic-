package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.OrderItem;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import jpabasic.ex1hellojpa.repository.order.query.OrderQueryDto;
import jpabasic.ex1hellojpa.repository.order.query.OrderQueryRepository;
import jpabasic.ex1hellojpa.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderControllers {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(){
        return orderQueryRepository.findOrderQueryDtos();
    }


    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private Address address;
        private List<OrderItemDto> orderItems;
        public OrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getUserName();
            this.orderDate = order.getOrderDate();
            this.address = order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto {
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
