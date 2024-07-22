package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Optional<Member> findByName(String name);
}
//JPA가 JPA리포지토리를 받고있으면 얘가 구현체를 자동으로 만들어줌.
//내가 스프링 빈에 등록하는게 아니라 JPA가 지가 구현체 만들어서 등록해줌