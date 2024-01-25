package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderTestApi {

    private final OrderRepository orderRepository;


    @GetMapping("/api/getOrders")
    public List<OrderDto> orderDtoList (){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> collect = orders.stream()
                .map(OrderDto::new)
                .toList();

        System.out.println("collect = " + collect);
        return collect;
    }
}
