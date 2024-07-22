package landvibe.springintro.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//jpa를 쓰려면 먼저 엔티티라는 것을 맵핑해야함. jpa는 인터페이스
//JPA는 자바 진영의 표준 인터페이스고 구현은 여러 업체들이 하는거임
//JPA는 객체랑 ORM(object,relational(관계형디비),mapping)기술
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //이 정보를 가지고 insert문
    //update문 등 다 만들 수 있음.
    private Long id; //임의의값 고객이 정하는 아이디가 아니라 데이터 구분을 위해 시스템이 저장하는 아이디.
    private String name; //고객이 적는거
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}