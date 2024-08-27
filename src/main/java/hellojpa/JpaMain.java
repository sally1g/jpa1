package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            /* Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);*/

            Member findMember =  em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            System.out.println("findMember:"+findMember.getId());
            System.out.println("findMember:"+findMember.getName());

            // Member 객체를 대상으로 (Member는 테이블이 아니다.)
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            for(Member member: result){
                System.out.println("member:"+member.getName());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        //code

        emf.close();
    }
}
