package landvibe.springintro.member.controller;

import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.dto.MemberCreateForm;
import landvibe.springintro.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(@ModelAttribute MemberCreateForm form) {
        Member member = new Member();
        member.setId(form.getId());
        member.setName(form.getName());
        memberService.create(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String member(Model model){
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
