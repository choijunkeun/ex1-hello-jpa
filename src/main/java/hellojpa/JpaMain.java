package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member referenceMember = em.getReference(Member.class, member1.getId());
            System.out.println("referenceMember.getClass() = " + referenceMember.getClass());

//            referenceMember.getUsername();  // 프록시 강제 초기화(JPA표준은 강제초기화가 없어서 이렇게해야함)
            Hibernate.initialize(referenceMember);  // 프록시 강제 초기화(Hibernate에서 구현되어있는것)
            System.out.println("referenceMember isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(referenceMember));


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
