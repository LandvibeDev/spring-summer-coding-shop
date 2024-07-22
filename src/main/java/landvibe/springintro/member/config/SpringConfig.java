package landvibe.springintro.member.config;

import jakarta.persistence.EntityManager;
import landvibe.springintro.aop.TimeTraceAop;
import landvibe.springintro.member.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import landvibe.springintro.member.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig { //둘 다 스프링 빈에 등록
    /*
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */

    //private EntityManager em;

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
     */

//  @Bean
//  public MemberRepository memberRepository() {
//      return new MemoryMemberRepository(); //나중에 레포지토리를 변경하면 다른거 일절 변경없이 얘만 수정해주면 끝
//      return new JdbcMemberRepository(dataSource);
//      return new JdbcTemplateMemberRepository(dataSource);
//      return new JpaMemberRepository(em);
//  }
}

/*
어떤 코드도 건들이지 않고 구현체를 확장.
스프링이 제공하는 config만 변경.
 */