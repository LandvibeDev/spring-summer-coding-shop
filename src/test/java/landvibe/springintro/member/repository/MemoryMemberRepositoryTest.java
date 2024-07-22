package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore(); //테스트가 실행되고 끝날때마다 한번씩 저장소를 다 치움->순서와 상관이 없어짐
    }
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);//리포지토리에 세이브
        //then
        Member result = repository.findById(member.getId()).get();//옵셔널에서 값을 꺼낼때는 .get()으로 꺼내기
        assertThat(result).isEqualTo(member); //같은지확인
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1); //result는 member1과 똑같다
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2); //사이즈는 2개가 나와야한당
    }
}
