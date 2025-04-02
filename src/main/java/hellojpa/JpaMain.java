package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
                // 객체를 생성한 상태(비영속)
                Member member = new Member();
                member.setId(101L);
                member.setName("회원1");

            System.out.println("=== START ===");
            em.persist(member);
            System.out.println("=== END ===");

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id :" + findMember.getId());
            System.out.println("findMember.name :" + findMember.getName());

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
