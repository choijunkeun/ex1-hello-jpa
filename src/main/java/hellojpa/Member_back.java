///*
// * 기본키 매핑 방법
// * - 직접 할당 : @Id만 사용
// * - 자동 생성 : @GeneratedValue
// *  @GeneratedValue(strategy = GenerationType.IDENTITY)
// *  > 기본키 생성을 데이터베이스에 위임
// * */
//package hellojpa;
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//public class Member_back {
//
//    @Id @GeneratedValue
//    @Column(name = "MEMBER_ID")
//    private Long id;
//
//    @Column(name = "USERNAME")
//    private String username;
//
//    @Embedded
//    private Address homeAddress;
//
//    @ElementCollection
//    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    @Column(name = "FOOD_NAME")
//    private Set<String> favoriteFoods = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();
//
////    @Embedded
////    @AttributeOverrides({
////            @AttributeOverride(name="city", column = @Column(name = "WORK_CITY")),
////            @AttributeOverride(name="street", column = @Column(name ="WORK_STREET")),
////            @AttributeOverride(name="zipcode", column = @Column(name ="WORK_ZIPCODE"))
////    })
////    private Address workAddress;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    public Address getHomeAddress() {
//        return homeAddress;
//    }
//
//    public void setHomeAddress(Address homeAddress) {
//        this.homeAddress = homeAddress;
//    }
//
//
//    //
////    @ManyToOne(fetch = FetchType.EAGER)
////    @JoinColumn
////    private Team team;
////
////
////    public Long getId() {
////        return id;
////    }
////
////    public String getUsername() {
////        return username;
////    }
////
////    public void setUsername(String username) {
////        this.username = username;
////    }
////
////    public void setTeam(Team team) {
////        this.team = team;
////    }
////
////    public Team getTeam() {
////        return team;
////    }
//}
//
