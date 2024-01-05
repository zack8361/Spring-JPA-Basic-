package jpabasic.ex1hellojpa.service;

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
    
    
    @Autowired private ExampleService exampleService;
    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setUserName("이찬호");
        exampleService.saveMem(member);
    }

    @Test
    public void 팀가입(){
        Team team = new Team();
        team.setName("연구 4팀");
        exampleService.saveteam(team);
    }

    @Test
    public void 회원팀매치(){
        Member findMember = exampleService.findById(1L);
        Team findTeam = exampleService.findByTeamId(1L);

        findMember.setTeam(findTeam);
        findTeam.getMembers().add(findMember);
    }
}