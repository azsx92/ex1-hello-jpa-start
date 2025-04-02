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

        try {
            //영속

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZ");
            // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

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
