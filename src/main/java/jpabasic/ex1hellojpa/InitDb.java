package jpabasic.ex1hellojpa;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.*;
import jpabasic.ex1hellojpa.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            Member member = createMember("userA","서울","영등포구","620-53");
            em.persist(member);


            Book book = createBook("SPRING1 BOOK",10000, 100);
            em.persist(book);

            Book book2 = createBook("SPRING2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem1 = OrderItem.createOrderItem(book, 10000, 1);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem);
            em.persist(order);
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }


        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setUserName(name);
            member.setAddress(new Address(city,street,zipcode));
            return member;
        }
    }
}

