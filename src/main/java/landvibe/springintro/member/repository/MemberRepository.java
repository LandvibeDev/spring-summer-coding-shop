package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장소에 저장됨.
    Optional<Member> findById(Long id); //없으면 null 반환. 요즘은 null 처리할 때 optional로 감싸서 반환.
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 모든 것을 반환.
}