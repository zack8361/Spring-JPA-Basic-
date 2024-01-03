package jpabasic.ex1hellojpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest

public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setName("kon");
        memberService.save(member);
    }
}