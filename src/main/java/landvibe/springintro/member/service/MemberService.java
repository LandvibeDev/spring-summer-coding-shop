package landvibe.springintro.member.service;

import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.repository.MemberRepository;
import landvibe.springintro.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     *회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복 회원 검증 메서드
            memberRepository.save(member);
            return member.getId(); //id반환
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }//중복회원잡는게 잘 작동하는지 보기위해 테스트케이스 활용
    /**
     *전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
