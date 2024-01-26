package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.OrderItem;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import jpabasic.ex1hellojpa.repository.order.query.OrderQueryDto;
import jpabasic.ex1hellojpa.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.*;


/**
 * Fetch join
 * 1대다 조인에서는 Row 가 증가하기 떄문에
 * Order 엔티티의 조회수도 증가하게 된다. -> distinct 추가.
 * 단점 : 페이징 불가능
 */
@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v2/orders")
    public List<OrderDto2> ordersV2(){
        return orderRepository.findAll().stream()
                .map(OrderDto2::new)
                .collect(toList());
    }
    @GetMapping("/api/v3/orders")
    public List<OrderDto2> ordersV3(){
        return orderRepository.findAllWithItem().stream()
                .map(OrderDto2::new)
                .collect(toList());
    }
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto2> ordersV31(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                     @RequestParam(value = "limit",defaultValue = "100") int limit){

        return orderRepository.findAllWithMemberDelivery(offset, limit).stream()
                .map(OrderDto2::new)
                .collect(toList());
    }

    @GetMapping("/api/v4.orders")
    public List<OrderQueryDto> ordersV4(){
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findOrderQueryDtos();

        return orderQueryDtos;
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
