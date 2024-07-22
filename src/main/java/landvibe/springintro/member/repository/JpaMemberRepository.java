package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; //jpa는 entityManager라는 걸로 모든게 동작
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member); //영구저장하다
        return member;
    }//이렇게하면 jpa가 인서트 쿼리 다 만들어서 db에 집어넣고 id까지 setid해줌
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    } //엔티티를 대상으로 쿼리를 날림 객체 자체를 select함
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}