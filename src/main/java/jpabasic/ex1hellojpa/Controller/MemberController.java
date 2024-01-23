package jpabasic.ex1hellojpa.Controller;


import jpabasic.ex1hellojpa.domain.Address;
import jpabasic.ex1hellojpa.domain.Member;
import jpabasic.ex1hellojpa.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";

    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){

        if(result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(),memberForm.getStreet(),memberForm.getZipcode());
        Member member = new Member();
        member.setUserName(memberForm.getName());
        member.setAddress(address);

        Long joinId = memberService.join(member);
        log.info(joinId + "가입");
        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();

        System.out.println("members = " + members);
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
