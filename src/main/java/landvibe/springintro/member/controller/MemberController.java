package landvibe.springintro.member.controller;

import org.springframework.stereotype.Controller;
import landvibe.springintro.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import landvibe.springintro.member.domain.Member;

import java.util.List;
@Controller //'스프링 컨테이너'라는 통이 생기는데, 거기에 MemberController 객체가 생성되고 스프링이 관리해
public class MemberController {
    private final MemberService memberService; //new MemberService() 라고 안하는 이유 : 여러 개를 생성할 필요가 없으니까.

    //생성자 주입
    @Autowired //스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결해줘 -> DI. 의존관계 주입
    public MemberController(MemberService memberService) { //객체 생성
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new") //get은 조회할 때 주로 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //post는 데이터를 폼같은 것에 넣어서 전달할 때 주로 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); //join 해서 멤버가 가입

        return "redirect:/"; //회원가입 끝났으니까 다시 홈화면으로 돌려보내
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); //멤버 전부를 끄집어올 수 있어
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
