package jpabasic.ex1hellojpa.EntityEx;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.enumc.OrderStatus;
import jpabasic.ex1hellojpa.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Autowired
    EntityService entityService;

    @Autowired
    EntityManager entityManager;
    @Test
    public void test1(){
        Member member = new Member();
        member.setName("kong");
        entityService.saveMem(member);

        Order order = Order.builder()
                .member(member)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.GOOD)
                .build();
        entityService.saveOrder(order);
    }
}