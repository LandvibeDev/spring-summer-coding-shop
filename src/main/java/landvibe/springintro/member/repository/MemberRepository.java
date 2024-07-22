package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장
    Optional<Member> findById(Long id); //id로 회원찾기
    Optional<Member> findByName(String name); //optional은 findByname이 없을수도 있잖아. 없으면 null로 반환되겠지
    //null로 반환되는 대신 Optional로 감싸서 반환
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트 반환
}