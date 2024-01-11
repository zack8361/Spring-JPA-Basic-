package jpabasic.ex1hellojpa.EntityEx.ItemList;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.EntityEx.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpqlTest {

    @Autowired private EntityManager em;
    @Test
    public void JpqlTest1(){
        List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE name = '이찬호'", Member.class)
                .getResultList();

        List resultList1 = em.createNativeQuery("SELECT * FROM MEMBER")
                .getResultList();
        System.out.println(resultList1);
    }
}
