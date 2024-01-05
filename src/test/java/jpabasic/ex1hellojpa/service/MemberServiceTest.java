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

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired private ExampleService exampleService;
    @Autowired private ExampleRepository exampleRepository;
    @Test
    public void 회원가입(){

        
//      팀 설정
        Team team = new Team();
        team.setName("TeamA");
        exampleService.saveteam(team);

//      유저 저장
        Member member = new Member();
        member.setUserName("memberA");
//      유저 컬럼에 매핑된 팀에 방금 설정한 팀 저장.
        member.setTeam(team);
        exampleService.saveMem(member);

        Member findMember = exampleService.findById(member.getId());

        List<Member> members = findMember.getTeam().getMembers();
        for (Member m : members) {
            System.out.println(m.getUserName());
        }
    }
}