package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();   // 양방향연관관계를 사용함으로서 member>team>member로 찾아옴

            for (Member m: members) {
                System.out.println("m= " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저 꼭 닫아줘야한다.(내부적으로 데이터베이스 커넥션을 물고 동작하므로 사용을 다하면 꼭 닫아야함)
        }

        emf.close();    // 웹 어플리케이션이라고 하면 WAS가 내려갈 때 닫아줘야한다.
    }
}
