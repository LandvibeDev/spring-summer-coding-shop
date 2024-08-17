package landvibe.springintro.member.service;

import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.ItemRepository;
import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Long create(Member member) {
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
    private void validateDuplicateMember(String memberName) {
        memberRepository.findByName(memberName)
                .ifPresent(i -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
}
