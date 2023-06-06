/*
* 연관관계의 주인(Owner)
* - 양방향 매핑 규칙
*   1. 객체의 두 관계중 하나를 연관관계의 주인으로 지정
*   2. 연관관계의 주인만이 외래 키를 관리(등록, 수정)
*   3. 주인이 아닌쪽은 읽기만 가능
*   4. 주인은 mappedBy 속성 사용 X
*   5. 주인이 아니면 mappedBy 속성으로 주인을 지정
*
*   - 누구를 주인으로 ?
*      - 외래키가 있는곳을 주인으로 정해라 (여기선 Member.team이 연관관계의 주인)
* */



package hellojpa;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

