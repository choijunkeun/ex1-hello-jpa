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
import java.util.Date;


@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)  // 데이터베이스 컬럼명은 name이야
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

