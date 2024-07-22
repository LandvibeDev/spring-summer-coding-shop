package landvibe.springintro.member.service;

import org.junit.jupiter.api.Test;
import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.repository.MemberRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행
@Transactional //test 실행할 때 transaction을 먼저 실행. test 후 roll back. -> 넣었던 데이터 다 없어져
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given : 무언가가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        //When : 이걸 실행했을 때
        Long saveId = memberService.join(member); //저장한 id가 리턴되기로 해놨으니까

        //Then : 결과가 이게 나와야해
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //멤버2도 spring이라 해

        //When
        memberService.join(member1); //문제가 없어야하고
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야해
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}

