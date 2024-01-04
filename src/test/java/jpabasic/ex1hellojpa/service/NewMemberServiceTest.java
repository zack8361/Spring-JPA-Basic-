package jpabasic.ex1hellojpa.service;

import jpabasic.ex1hellojpa.Entity.NewMember;
import jpabasic.ex1hellojpa.enumc.RoleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest

public class NewMemberServiceTest {

    @Autowired NewMemberService newMemberService;

    @Test
    public void newMem저장(){
        NewMember newMember = new NewMember();
        newMember.setUserName("이찬호");
        newMember.setRoleType(RoleType.USER);
        newMember.setAge(26);

        newMemberService.saveNewMem(newMember);
    }
}