package jpabasic.ex1hellojpa.service;

import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@SpringBootTest
@RunWith(SpringRunner.class)

public class ItemServiceTest {

    @Autowired
    EntityManager em;
    @Test
    public void updateTest() throws Exception {



    }
}