package jpabasic.ex1hellojpa.service;


import jpabasic.ex1hellojpa.EntityEx.Member;
import jpabasic.ex1hellojpa.EntityEx.Order;
import jpabasic.ex1hellojpa.repository.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EntityService {

    private final EntityRepository entityRepository;

    public void saveMem(Member member){
        entityRepository.saveMem(member);
    }

    public void saveOrder(Order order) {
        entityRepository.saveOrder(order);
    }
}
