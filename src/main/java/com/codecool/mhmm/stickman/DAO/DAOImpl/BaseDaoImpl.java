package com.codecool.mhmm.stickman.DAO.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;

class BaseDaoImpl {

    private EntityManagerFactory emf;
    EntityManager em;
    private EntityTransaction et;
    CriteriaBuilder cb;

    BaseDaoImpl(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
        this.et = em.getTransaction();
        this.cb = em.getCriteriaBuilder();
    }
}
