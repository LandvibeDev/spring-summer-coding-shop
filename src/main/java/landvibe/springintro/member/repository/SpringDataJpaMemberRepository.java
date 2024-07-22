package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import landvibe.springintro.member.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}

//구현체를 알아서 만들어서 자동으로 스프링빈을 등록해 -> 우린 이걸 가져다 쓰기만 하면 돼