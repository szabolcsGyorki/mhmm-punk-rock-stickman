package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.*;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Init {

    public static void init(EntityManager em) {

        Armor armor = new Armor("Sacred armor", 40, 50);
        Weapon weapon = new Weapon("Sword of thousand darkness", 80, 234, 165);
        Weapon weapon1 = new Weapon("Mythical Sword", 1000, 120, 1);
        Weapon weapon2 = new Weapon("Colossus Blade", 120, 30, 12);
        Weapon weapon3 = new Weapon("Hydra Bow", 42, 3, 1);
        Weapon weapon4 = new Weapon("Healing Wand",100, -10, -100);
        Armor armor2 = new Armor("Shadow Plate", 100, 30);
        Armor armor3 = new Armor("Kraken Shell", 10, 100);
        Armor armor4 = new Armor("Diamond Mail", 80, 20);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(armor);
        em.persist(armor2);
        em.persist(armor3);
        em.persist(armor4);
        em.persist(weapon);
        em.persist(weapon1);
        em.persist(weapon2);
        em.persist(weapon3);
        em.persist(weapon4);

        transaction.commit();

    }
}
