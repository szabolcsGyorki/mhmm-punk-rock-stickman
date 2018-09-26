package com.codecool.mhmm.stickman.DAO.DAOImpl;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();

    @Test
    void testInstanceCreated() {
        PlayerDAOImpl dao = new PlayerDAOImpl(em);
        assertNotNull(dao);
    }
}