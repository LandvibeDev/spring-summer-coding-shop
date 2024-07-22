package landvibe.springintro.member.config;

import landvibe.springintro.member.repository.*;
import landvibe.springintro.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean //스프링 빈을 등록할거야 //이게 싱글톤 ..??이라고하셧나그럼이밑에잇는코드가 한객체인가
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    // @Bean
    //public MemberRepository memberRepository() {
    //return new MemoryMemberRepository();
    //return new JdbcMemberRepository(dataSource);
    //return new JdbcTemplateMemberRepository(dataSource);
    //return new JpaMemberRepository(em);

    //}
}