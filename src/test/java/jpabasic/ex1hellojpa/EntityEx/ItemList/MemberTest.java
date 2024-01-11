package jpabasic.ex1hellojpa.EntityEx.ItemList;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.EntityEx.Address;
import jpabasic.ex1hellojpa.EntityEx.Member;
import jpabasic.ex1hellojpa.EntityEx.Order;
import jpabasic.ex1hellojpa.enumc.OrderStatus;
import jpabasic.ex1hellojpa.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MemberTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityService entityService;

    @Test
    public void saveMemberWithReference(){
        Member member = new Member();
        member.setName("이찬호");
        entityManager.persist(member);
    }
    @Test
    public void getOrder(){
        Member member = new Member();
        member.setName("이찬호");
        member.setAddress(new Address("city","street","zipcode"));
        entityService.saveMem(member);
    }


    @Test
    public void setAddress(){
        Address address = new Address("city","street","100000");

        Member member = new Member();
        member.setAddress(address);

        Member member1 = new Member();
        member1.setAddress(address);
    }

}
