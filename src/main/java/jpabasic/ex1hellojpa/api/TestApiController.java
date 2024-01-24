package jpabasic.ex1hellojpa.api;


import jakarta.validation.Valid;
import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.repository.MemberRepository;
import jpabasic.ex1hellojpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor

@RequestMapping("/test")
public class TestApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    /**
     * 회원 수정 로직
     */

    @PutMapping("/api/updateMember/{id}")
    public ResponseUpdate responseUpdate(
            @PathVariable("id") Long memberId,
            @RequestBody @Valid RequestUpdate requestUpdate){

        memberService.update(memberId,requestUpdate.getName());

        return new ResponseUpdate(memberId,requestUpdate.getName());
    }



    @Data
    @AllArgsConstructor
    static class ResponseUpdate{
        private Long id;
        private String name;
    }

    @Data
    static class RequestUpdate{
        @NotNull(message = "여기는 비어있으면 안됩니다")
        private String name;
    }



    /**
     * 리스트 뽑기
     */
    @GetMapping("/api/getMembers")
    public ResponseMember getMembers(){
        List<Member> members = memberService.findMembers();

        List<MemberDto> collect = members.stream()
                .map(m -> new MemberDto(m.getUserName(),m.getAddress()))
                .collect(Collectors.toList());

        System.out.println("collect = " + collect);

        return new ResponseMember(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class ResponseMember<T>{
        private int count;
        private T data;
    }
    
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private Address address;
    }

    /**
     * 회원 생성
     */

    @PostMapping("/api/createMember")
    public CreateResponse createMember(@RequestBody @Valid CreateRequest request){

        Member member = new Member();
        member.setUserName(request.getName());

        Long join = memberService.join(member);

        return new CreateResponse(join,LocalDateTime.now());
    }

    @Data
    @AllArgsConstructor
    public static class CreateResponse{
        private Long id;
        private LocalDateTime joinDateTime;
    }

    @Data
    public static class CreateRequest{
        @NotNull(message = "반드시 있어야하는 항목이")
        private String name;
    }
}
