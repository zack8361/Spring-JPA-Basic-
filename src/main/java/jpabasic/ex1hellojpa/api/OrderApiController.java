package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;



    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());

    }
}
