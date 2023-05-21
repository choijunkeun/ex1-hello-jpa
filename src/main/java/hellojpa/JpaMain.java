package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory는 웹서버가 올라오는 시점에 딱 1개만 생성(DB당 1개만)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 고객의 요청이 올 때마다 사용 했다가, em.close()로 버렸다가 함.
        // 따라서 EntityManager는 쓰레드간에 공유X(사용하고 버려야함)
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행 해야한다.
        EntityManager em = emf.createEntityManager();   // 데이터베이스 커넥션 받은거로 생각하면 됨

        EntityTransaction tx = em.getTransaction();// 트랜잭션 얻은것
        tx.begin();

        try {
            /*  // insert
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member); // Insert
             */

            /*   // select
            Member findMember = em.find(Member.class, 1L);  // 가장 단순한 조회 방법
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
             */

            /*  // delete
            Member findMember = em.find(Member.class, 1L);  // 1. 찾은애를,
            em.remove(findMember);                             // 2. 지운다
             */

            /* // update
            Member findMember = em.find(Member.class, 2L);  // 1. 찾은애를,
            findMember.setName("HelloJPA");                    // 2. 이름을 HelloJPA로 바꾼다.
            // update에서는 insert와 달리 persist() 안해도 된다.
            // JPA를 통해서 Entity를 가져오면, JPA가 관리를 하고, 변경된 내용이 있으면 Tx - Commit시점에서 확인을 한다.
            // 그에 따라 자바 컬렉션을 다루는것 처럼 설계되어서 persist안해도 setName으로만 update가 가능.
             */


            /* JPQL
            JPA를 사용하면 엔티티 객체를 중심으로 개발
            문제는 검색 쿼리
            검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            모든 DB데이터를 객체로 변환해서 검색하는 것은 불가능
            애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요

            JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
            SQL문법과 유사(SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원)
            JPQL은 엔티티 객체를 대상으로 쿼리 <> SQL은 데이터베이스 테이블을 대상으로 쿼리
            SQL을 추상화해서 특정 데이터베이스 SQL에 의존적이지 않음
            JPQL을 한마디로 정의하면 객체 지향 SQL
            */

            // JPA는 테이블을 대상으로 코드를 짜지 않음. Member객체를 대상으로 콜을 하는것. "Member객체를 다 가져와"
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)  // 1번부터
                    .setMaxResults(10)  // 10개 가져와 (페이징 처리에 사용)
                    .getResultList();

            for(Member member : result) {
                System.out.println("member.name = " + member.getName());
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
