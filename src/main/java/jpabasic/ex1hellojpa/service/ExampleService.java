package jpabasic.ex1hellojpa.service;


import jpabasic.ex1hellojpa.Entity.Member;
import jpabasic.ex1hellojpa.Entity.Team;
import jpabasic.ex1hellojpa.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public void saveteam(Team team){
        exampleRepository.saveTeam(team);
    }

    public void saveMem(Member member){
        exampleRepository.saveMem(member);
    }

    public Member findById(Long id){
        return exampleRepository.findById(id);
    }

    public Team findByTeamId(Long id){
        return exampleRepository.findByTeamId(id);
    }
}
