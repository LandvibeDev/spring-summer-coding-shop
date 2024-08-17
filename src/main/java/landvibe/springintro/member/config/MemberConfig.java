package landvibe.springintro.member.config;

import landvibe.springintro.item.repository.ItemRepository;
import landvibe.springintro.item.service.ItemService;
import landvibe.springintro.member.repository.MemberRepository;
import landvibe.springintro.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {
    private final MemberRepository memberRepository;
    public MemberConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
