package jpabasic.ex1hellojpa;

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
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void test1(){
        // given
        Member member = new Member();
        member.setName("Lee chan ho");
        // when
        memberService.save(member);

        // then
    }

    @Test
    public void 회원찾기(){

        // given
        // 생략. 1L 로뽑을거임.

        // when
        Member findMember = memberService.findById(1L);

        // then
        System.out.println("findMember = " + findMember);

    }

    @Test
    public void 회원전체조회(){

        List<Member> all = memberService.findAll();
        System.out.println("all = " + all);
    }
}