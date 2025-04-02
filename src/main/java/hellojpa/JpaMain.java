package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        // 엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
        tx.begin();
        // flush란 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영하는 것이다.
        // 그리고 나서 transaction이 커밋이 되는 것이다.
        try {
            //영속

            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.close();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("-======================-");
            // 커밋하는 순간 데이터 베이스에 INSERT SQL을 보낸다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
