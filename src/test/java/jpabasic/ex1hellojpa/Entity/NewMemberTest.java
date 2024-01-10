package jpabasic.ex1hellojpa.Entity;

import jakarta.persistence.EntityManager;
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
public class NewMemberTest {

    @Autowired private EntityManager entityManager;
    @Test
    public void lazyLoadingTest(){
        Team team = new Team();
        team.setName("teamA");
        entityManager.persist(team);

        NewMember newMember = new NewMember();
        newMember.setUserName("이찬호");
        newMember.setAge(26);
        newMember.setTeam(team);

        entityManager.persist(newMember);
        entityManager.flush();
        entityManager.clear();

        NewMember newMember1 = entityManager.find(NewMember.class, newMember.getId());
        try{
            System.out.println(newMember.getTeam().getClass());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}