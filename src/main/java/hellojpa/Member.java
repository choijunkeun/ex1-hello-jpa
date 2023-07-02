/*
 * 기본키 매핑 방법
 * - 직접 할당 : @Id만 사용
 * - 자동 생성 : @GeneratedValue
 *  @GeneratedValue(strategy = GenerationType.IDENTITY)
 *  > 기본키 생성을 데이터베이스에 위임
 * */


package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable =false)
    private Team team;

    @OneToOne   // 1:1관계
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();
//
//    private String createBy;
//    private LocalDateTime createdDate;
//    private String lastModifiedBy;
//    private LocalDateTime lastModifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}

