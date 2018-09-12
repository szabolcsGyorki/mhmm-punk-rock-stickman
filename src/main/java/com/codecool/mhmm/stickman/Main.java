package com.codecool.mhmm.stickman;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
        EntityManager em = emf.createEntityManager();

        TestHibernate test = new TestHibernate();
        test.setDescription("Ez egy test");
        test.setName("Proba");
        test.setValue(347);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(test);
        transaction.commit();

        TestHibernate foundTest = em.find(TestHibernate.class, test.getId());
        System.out.println(foundTest.getName());
        System.out.println(foundTest.getDescription());
        System.out.println(foundTest.getValue());

        em.close();
        emf.close();
    }
}
