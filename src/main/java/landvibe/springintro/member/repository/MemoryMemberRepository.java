package landvibe.springintro.member.repository;
import landvibe.springintro.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //key는 id 값은 member
    private static long sequence = 0L; //012 처럼 key값 생성해줌
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //ID는 시스템이 정해줌
        store.put(member.getId(), member); //맵에 저장됨
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 없으면 null이 반환되지않도록 optional로 감싸줌.
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //멤버들 반환
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member.getName이 파라미터로 넘어온 name이랑 같은지
                .findAny(); //맵에서 돌면서 하나 찾으면 걔를 그냥 반환. 끝까지 돌렸는데 없으면 optional null반환
    }
    public void clearStore() {
        store.clear();
    }
}
