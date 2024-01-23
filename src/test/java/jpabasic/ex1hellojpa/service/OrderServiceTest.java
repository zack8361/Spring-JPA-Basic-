package jpabasic.ex1hellojpa.service;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.domain.Order;
import jpabasic.ex1hellojpa.domain.item.Book;
import jpabasic.ex1hellojpa.domain.item.Item;
import jpabasic.ex1hellojpa.enums.OrderStatus;
import jpabasic.ex1hellojpa.exception.NotEnoughStockException;
import jpabasic.ex1hellojpa.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.fail;


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
        Member member = createMember();
        Item book = createBook("조제 호랑이와 물고기들", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);
        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getOrderStatus());

    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("조제 호랑이와 물고기들", 10000, 10);
        int orderCount = 10;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        System.out.println("orderId = " + orderId);
        //then
        fail("!! 제고 수량 부족 예외가 발생해야 한다 !!");


    }
    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setItemName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setUserName("이찬호");
        member.setAddress(new Address("서울","영등포동","123-123"));
        em.persist(member);
        return member;
    }



}