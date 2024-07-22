package landvibe.springintro.member.controller;

import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;
    @Autowired
    //@autowired private final~~ <-필드 주입방식
    public MemberController(MemberService memberService) { //생성자 주입 방식
        this.memberService = memberService;
    }
    @GetMapping(value = "/members/new") //url창에 딱 치는건 Get맵핑
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping(value = "/members/new") //포스트 맵핑은 데이터를 폼깥은데 넣어서 전달할 때 씀
    //데이터 등록할때는 보통 post를 씀
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/"; //회원가입 끝났으니까 홈화면으로 돌림
    }
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}