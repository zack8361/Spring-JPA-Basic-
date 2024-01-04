package jpabasic.ex1hellojpa.service;

import jpabasic.ex1hellojpa.Entity.Member;
import jpabasic.ex1hellojpa.Entity.Team;
import jpabasic.ex1hellojpa.repository.ExampleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest

public class MemberServiceTest {

    @Autowired private ExampleService exampleService;
    @Autowired private ExampleRepository exampleRepository;
    @Test
    public void 회원가입(){

        Team team = new Team();
        team.setName("TeamA");
        exampleService.saveteam(team);
        Member member = new Member();

        member.setUserName("memberA");
        member.setTeamId(team.getId());
        exampleService.saveMem(member);
    }
}