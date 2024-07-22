package landvibe.springintro.member.service;

import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.repository.MemberRepository;
import landvibe.springintro.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService { //이에 해당하는 test 만들기 -> ctrl+shift+T 단축키
    private final MemberRepository memberRepository;
    //MemberService를 스프링이 생성을 할 때 service네? 하고 생성자를 호출.

    //@Autowired //이게 autowired이면 memberRepository가 필요하구나. 하고 스프링 컨테이너에 있는 것을 넣어줘
    public MemberService(MemberRepository memberRepository) { //외부에서 memberRepository를 넣어줘 -> dependency injection
        this.memberRepository = memberRepository;
    }

    /**
     *
     회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //id 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //null이 아니고 어떤 값이 있으면 이 로직 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *
     전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}