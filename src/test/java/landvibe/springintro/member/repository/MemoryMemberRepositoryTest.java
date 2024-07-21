package landvibe.springintro.member.repository;

import landvibe.springintro.member.domain.Member;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //test들은 서로 의존관계 없어야해.!!
    public void afterEach() { //test가 실행되고 끝날때마다 한번씩 저장소를 지워. -> 순서상관없어져.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //내가 넣은게 제대로 들어갔는지 검증을 해. 반환타입이 optional. 여기서 꺼낼 때는 get을 사용.
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result); member==result 인지 확인.
        assertThat(member).isEqualTo(result); //역시 result 자리에 null을 넣으면 에러가 나.
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //정교한 test를 위해 객체 하나 더 생성. -> shift+F6 누르면 한번에 수정 가능.
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get(); //spring1을 찾아보는거야. get으로 꺼내봐.
        //then
        assertThat(result).isEqualTo(member1);
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
        assertThat(result.size()).isEqualTo(2);
    }
}

/*
findByName에서 에러가 나. 왜일까?
모든 test의 순서는 보장X. findAll이 먼저 실행됨. spring1과 spring2가 이미 저장된거야.
그래서 findByName에서 이전에 저장된게 나와버려.

따라서..!! test가 하나 끝나고나면 data를 clear 해줘야해.
*/