package jpabasic.ex1hellojpa.cascade;

import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParentChildTest {


    @Autowired
    EntityManager entityManager;
    @Test
    public void ParentTest(){
        Parent parent = new Parent();
        parent.setName("엄마 와 아빠");

        entityManager.persist(parent);
        Child child1 = new Child();
        child1.setName("이찬호");
        
        Child child2 = new Child();
        child2.setName("이재호");
        
        child1.addParent(parent);
        child2.addParent(parent);

        entityManager.persist(child1);
        entityManager.persist(child2);

        entityManager.flush();

        Parent parent1 = entityManager.find(Parent.class, parent.getId());
        List<Child> childList = parent1.getChildList();
        for (Child child : childList) {
            System.out.println(child.getName());
        }
    }
}