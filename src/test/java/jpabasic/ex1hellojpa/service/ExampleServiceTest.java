package jpabasic.ex1hellojpa.service;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.Entity.Member;
import jpabasic.ex1hellojpa.Entity.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleServiceTest {

    @Autowired private EntityManager em;
    
    @Autowired private ExampleService exampleService;

    @Test
    public void test1(){
        Team team = new Team();
        team.setName("연구 4팀");
        exampleService.saveteam(team);

        Member member = new Member();
        member.setUserName("이찬호");
        member.setTeam(team);
        exampleService.saveMem(member);
    }
}