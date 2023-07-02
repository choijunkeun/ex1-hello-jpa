package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setCreateBy("kim");
            member.setCreatedDate(LocalDateTime.now());
            member.setUsername("user1");
            em.persist(member);

            em.flush();
            em.clear();



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저 꼭 닫아줘야한다.(내부적으로 데이터베이스 커넥션을 물고 동작하므로 사용을 다하면 꼭 닫아야함)
        }

        emf.close();    // 웹 어플리케이션이라고 하면 WAS가 내려갈 때 닫아줘야한다.
    }
}
