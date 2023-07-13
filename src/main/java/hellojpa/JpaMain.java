package hellojpa;


/**
 *  값 타입은 깔끔하게 전부 불변으로 만들어라
 *  값 타입 컬렉션은 !!정말!! 단순할 때 쓰면 된다. (추적할 필요도 없고 값이 바뀌어도 업데이트 칠 필요가 없을때)
 *  단순하지 않다면 그냥 entity로 써서 매핑해서 쓰는게 낫다. (웬만하면 entity 사용)
 *
 *  다시 말하면 값 타입은 정말 값 타입이라 판단될 때만 사용해야하고
 *  엔티티와 값 타입을 혼동해서 엔티티를 값 타입으로 만들면 안된다.
 *  식별자가 필요하고, 지속해서 값을 추적, 변경해야 한다면 그것은 값타입이 아닌 엔티티이다.
 *
 *  # 엔티티 타입의 특징
 *   - 식별자 O
 *   - 생명주기관리
 *   - 공유
 *
 *  # 값 타입의 특징
 *   - 식별자 X
 *   - 생명 주기를 엔티티에 의존
 *   - 공유하지 않는것이 안전(복사해서 사용해야함)
 *   - 불변 객체로 만드는 것이 안전
 * */
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "12345"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "oldstreet1", "12345"));
            member.getAddressHistory().add(new AddressEntity("old2", "oldstreet2", "12345"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========START==============");
            Member findMember = em.find(Member.class, member.getId());


            // homeCity -> newCity 수정할 때
//            findMember.getHomeAddress().setCity("newCity"); 값 타입 절대 이렇게 하지 말것

            Address a = findMember.getHomeAddress();
            // 값타입은 통으로 갈아 끼워야함
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 값타입 컬렉션 수정
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");


//            findMember.getAddressHistory().remove(new AddressEntity("old1", "oldstreet1", "12345"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1", "newstreet1", "12345"));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); // 엔티티매니저 꼭 닫아줘야한다.(내부적으로 데이터베이스 커넥션을 물고 동작하므로 사용을 다하면 꼭 닫아야함)
        }

        emf.close();    // 웹 어플리케이션이라고 하면 WAS가 내려갈 때 닫아줘야한다.
    }



}
