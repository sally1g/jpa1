package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member findMember =  em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            System.out.println("findMember:"+findMember.getId());
            System.out.println("findMember:"+findMember.getName());
           /* Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);*/

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
