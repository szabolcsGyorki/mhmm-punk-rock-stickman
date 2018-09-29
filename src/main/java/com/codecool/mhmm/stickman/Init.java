package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Init {

    public static void init(EntityManager em) {

        Armor armor1 = new Armor("Sacred Armor", 14030, 40);
        Armor armor2 = new Armor("Shadow Plate", 100, 30);
        Armor armor3 = new Armor("Kraken Shell", 10, 100);
        Armor armor4 = new Armor("Diamond Mail", 80, 20);
        Armor armor5 = new Armor("Shining Armor", 1300, 50);
        Armor armor6 = new Armor("Flaming Armor", 1300, -10);
        Armor armor7 = new Armor("Invisible Armor", 1300, 0);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(armor1);
        em.persist(armor2);
        em.persist(armor3);
        em.persist(armor4);
        em.persist(armor5);
        em.persist(armor6);
        em.persist(armor7);

        Weapon weapon1 = new Weapon("RNG Sword", 1000, 1, 120);
        Weapon weapon2 = new Weapon("Colossus Blade", 120, 12, 30);
        Weapon weapon3 = new Weapon("Hydra Bow", 42, 1, 3);
        Weapon weapon4 = new Weapon("Healing Wand",100, -100, -10);
        Weapon weapon5 = new Weapon("Flaming Flamingo", 5, 40, 41);
        Weapon weapon6 = new Weapon("Ice Shard", 0, 0, 2);
        Weapon weapon7 = new Weapon("Cthulhus book of Insanity", 600, -600, 600);
        Weapon weapon8 = new Weapon("Forged Sword", 15, 22, 30);
        Weapon weapon9 = new Weapon("Magma Mace", 3010, 17, 55);
        Weapon weapon10 = new Weapon("Melted Mace", 10, 7, 15);
        Weapon weapon11 = new Weapon("Melted ice", 0, 0, 0);
        Weapon weapon12 = new Weapon("Mythical sword", 15, 20, 100);
        Weapon weapon13 = new Weapon("Basic Bow", 10, 0, 3);
        Weapon weapon14 = new Weapon("Basic Sword", 10, 3, 5);
        Weapon weapon15 = new Weapon("Cruel Crude Candle", 14, 3, 8);
        Weapon weapon16 = new Weapon("Phoenix Pike", 14, 33, 62);
        Weapon weapon17 = new Weapon("Gandalfs lost and found staff", 2000, 1, 1);
        Weapon weapon18 = new Weapon("Purging Pebble", 1, 39, 40);

        em.persist(weapon1);
        em.persist(weapon2);
        em.persist(weapon3);
        em.persist(weapon4);
        em.persist(weapon5);
        em.persist(weapon6);
        em.persist(weapon7);
        em.persist(weapon8);
        em.persist(weapon9);
        em.persist(weapon10);
        em.persist(weapon11);
        em.persist(weapon12);
        em.persist(weapon13);
        em.persist(weapon14);
        em.persist(weapon15);
        em.persist(weapon16);
        em.persist(weapon17);
        em.persist(weapon18);

        transaction.commit();

    }
}
