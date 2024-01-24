package jpabasic.ex1hellojpa.api;


import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newApi")
public class NewApi {

    private final MemberService memberService;



    @GetMapping("/members")
    public ResponseMembers getMembers(){

        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getUserName()))
                .toList();

        return new ResponseMembers(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }

    
}
