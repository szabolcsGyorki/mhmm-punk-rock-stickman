package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.*;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Init {

    public static void init(EntityManager em) {

        Player player = new Player(10, 20, "John");
        Player player1 = new Player(5, 10, "Sepiroth");
        Armor armor = new Armor("Sacred armor", 40, 50);
        Weapon weapon = new Weapon("Sword of thousand darkness", 80, 234, 165);
        Weapon weapon1 = new Weapon("Mythical Sword", 1000, 120, 1);
        Weapon weapon2 = new Weapon("Colossus Blade", 120, 30, 12);
        Weapon weapon3 = new Weapon("Hydra Bow", 42, 3, 1);
        Weapon weapon4 = new Weapon("Healing Wand",100, -10, -100);
        Armor armor2 = new Armor("Shadow Plate", 100, 30);
        Armor armor3 = new Armor("Kraken Shell", 10, 100);
        Armor armor4 = new Armor("Diamond Mail", 80, 20);
        player.addItemToInventory(weapon);
        player.addItemToInventory(armor);
        player.setFullBody(armor);
        player.setWeapon(weapon);
        player1.addItemToInventory(weapon1);
        player1.addItemToInventory(weapon1);
        player1.addItemToInventory(weapon2);
        player1.addItemToInventory(armor2);
        player1.addItemToInventory(armor3);
        player1.setWeapon(weapon2);
        player1.setFullBody(armor2);

        Enemy enemy1 = new Slime(1,2,1);
        Enemy enemy2 = new Orc(1,2,1);
        Enemy enemy3 = new Dragon(1,2,1);
        Enemy enemy4 = new Skeleton(1,2,1);


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
        em.persist(player);
        em.persist(player1);
        em.persist(enemy1);
        em.persist(enemy2);
        em.persist(enemy3);
        em.persist(enemy4);
        transaction.commit();

    }
}
