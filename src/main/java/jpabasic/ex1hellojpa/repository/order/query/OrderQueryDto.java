package jpabasic.ex1hellojpa.repository.order.query;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItemQueryDtoList;
}
