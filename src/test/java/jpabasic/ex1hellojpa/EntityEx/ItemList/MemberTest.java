package jpabasic.ex1hellojpa.EntityEx.ItemList;


import jakarta.persistence.EntityManager;
import jpabasic.ex1hellojpa.EntityEx.Address;
import jpabasic.ex1hellojpa.EntityEx.Member;
import jpabasic.ex1hellojpa.EntityEx.Order;
import jpabasic.ex1hellojpa.enumc.OrderStatus;
import jpabasic.ex1hellojpa.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityService entityService;

    @Test
    public void saveMemberWithReference(){

        try {
            Member member = new Member();
            member.setName("이찬호");
            entityService.saveMem(member);

            entityManager.flush();
            entityManager.clear();
//        Member findMember = entityManager.find(Member.class, member.getId());
//        System.out.println(findMember.getName() + "/" + findMember.getId());

            Member findMember = entityManager.getReference(Member.class, member.getId());
            System.out.println(findMember.getId());
            entityManager.detach(findMember);
            System.out.println(findMember.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void getOrder(){
        Member member = new Member();
        member.setName("이찬호");
        member.setAddress(new Address("city","street","zipcode"));
        entityService.saveMem(member);
    }
}
