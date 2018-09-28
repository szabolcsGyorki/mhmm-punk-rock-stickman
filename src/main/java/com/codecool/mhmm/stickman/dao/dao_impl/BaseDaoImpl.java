package com.codecool.mhmm.stickman.dao.dao_impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;

class BaseDaoImpl {

    EntityManager em;
    EntityTransaction transaction;
    CriteriaBuilder cb;

    BaseDaoImpl(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
        this.cb = em.getCriteriaBuilder();
    }
}
