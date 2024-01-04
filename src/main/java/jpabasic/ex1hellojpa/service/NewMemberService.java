package jpabasic.ex1hellojpa.service;


import jpabasic.ex1hellojpa.Entity.NewMember;
import jpabasic.ex1hellojpa.repository.NewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NewMemberService {

    private final NewMemberRepository newMemberRepository;
    public void saveNewMem(NewMember newMember){
        newMemberRepository.save(newMember);
    }
}
