package jpabasic.ex1hellojpa.service;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.item.Book;
import jpabasic.ex1hellojpa.domain.item.Item;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {


    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setUserName("이찬호");
        member.setAddress(new Address("서울","영등포동","123-123"));
        em.persist(member);

        Item book = new Book();
        book.setItemName("조제 호랑이와 물고기들");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getOrderStatus());

    }
    @Test
    public void 주문취소() throws Exception {
        //given


        //when


        //then

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given


        //when


        //then

    }
}