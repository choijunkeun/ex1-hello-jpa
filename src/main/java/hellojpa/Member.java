package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity         // default : (name= "Member")
//@Table(name = "MBR")    // 데이터베이스 테이블 MBR과 매핑됨
public class Member {
   /*
    @Id
    private Long id;

    @Column(unique = true, length = 10) // DDL 생성 기능, DDL생성 기능은 DDL을 자동생성할 떄만 사용되고
    private String name;                 // JPA의 실행로직에는 영향을 주지 않는다.
    private int age;
    */

    @Id
    private Long id;

    @Column(name = "name")  // 데이터베이스 컬럼명은 name이야
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)    // default : ORDINAL이지만 무조건 STRING으로 쓰자.
    private RoleType roleType;      // ex) Enum클래스에 USER, ADMIN -> 0,1 로 되어있다가 GUEST를 앞에 추가하면
                                    // GUEST가 0이 되면서 꼬여버림

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate       testLocalDate;
    private LocalDateTime   testLocalDateTime;

    @Lob   // 매핑하는 필드 타입이 String이면 CLOB 매핑, 나머지는 BLOB매핑
    private String description;

    @Transient          // 매핑 하기 싫을 때 사용
    private int temp;   // DB랑 관련 없이 쓰고 싶으면 @Transient 사용(메모리에서만 사용)

    public Member() {
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}

