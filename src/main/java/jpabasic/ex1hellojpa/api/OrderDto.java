package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private final Long id;
    private final String name;
    private final OrderStatus orderStatus;
    private final Address address;
    private final LocalDateTime orderDate;
    public OrderDto(Order order){
        this.id = order.getId();
        this.name = order.getMember().getUserName();
        this.orderStatus = order.getOrderStatus();
        this.address = order.getDelivery().getAddress();
        this.orderDate = order.getOrderDate();
    }
}
