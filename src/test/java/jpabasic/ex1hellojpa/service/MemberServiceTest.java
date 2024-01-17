package jpabasic.ex1hellojpa.service;

import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {


    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setUserName("이찬호");

        //when
        Long joinId = memberService.join(member);


        //then
        assertEquals(member, memberRepository.findOne(joinId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원가입() throws Exception {
        //given
        Member member1 = new Member();
        member1.setUserName("이찬호1");

        Member member2 = new Member();
        member2.setUserName("이찬호1");

        //when
        memberService.join(member1);
        memberService.join(member2);


        //then (여기 오면 안되는 코드)
        fail("예외가 발생해야 한다.");
    }
}