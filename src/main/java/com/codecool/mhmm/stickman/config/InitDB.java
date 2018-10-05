package com.codecool.mhmm.stickman.config;

import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Dragon;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Orc;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Skeleton;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Slime;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.services.HealthHandler;
import com.codecool.mhmm.stickman.services.ItemHandler;
import com.codecool.mhmm.stickman.services.LevelGenerator;

import java.util.Random;

public class InitDB {

    private ItemsDAO itemsDAO;
    private LevelDAO levelDAO;
    private EnemyDAO enemyDAO;
    private LevelGenerator levelGenerator;
    private HealthHandler healthHandler;
    private ItemHandler itemHandler;

    public InitDB(ItemsDAO itemsDAO, LevelDAO levelDAO, EnemyDAO enemyDAO,
                  LevelGenerator levelGenerator, HealthHandler healthHandler, ItemHandler itemHandler) {
        this.itemsDAO = itemsDAO;
        this.levelDAO = levelDAO;
        this.enemyDAO = enemyDAO;
        this.levelGenerator = levelGenerator;
        this.healthHandler = healthHandler;
        this.itemHandler = itemHandler;
    }

    public void init() {
        initArmors();
        initWeapons();
        initEnemies();
        initLoots();
        initLevels();
    }

    private void initArmors() {
        itemsDAO.saveNew(new Armor("Sacred Armor", 14030, 40));
        itemsDAO.saveNew(new Armor("Shadow Plate", 100, 30));
        itemsDAO.saveNew(new Armor("Kraken Shell", 10, 100));
        itemsDAO.saveNew(new Armor("Diamond Mail", 80, 20));
        itemsDAO.saveNew(new Armor("Shining Armor", 1300, 50));
        itemsDAO.saveNew(new Armor("Flaming Armor", 1300, -10));
        itemsDAO.saveNew(new Armor("Invisible Armor", 1300, 0));
    }

    private void initWeapons() {
        itemsDAO.saveNew(new Weapon("RNG Sword", 1000, 1, 120));
        itemsDAO.saveNew(new Weapon("Colossus Blade", 120, 12, 30));
        itemsDAO.saveNew(new Weapon("Hydra Bow", 42, 1, 3));
        itemsDAO.saveNew(new Weapon("Healing Wand",100, -100, -10));
        itemsDAO.saveNew(new Weapon("Flaming Flamingo", 5, 40, 41));
        itemsDAO.saveNew(new Weapon("Ice Shard", 0, 0, 2));
        itemsDAO.saveNew(new Weapon("Cthulhus book of Insanity", 600, -600, 600));
        itemsDAO.saveNew(new Weapon("Forged Sword", 15, 22, 30));
        itemsDAO.saveNew(new Weapon("Magma Mace", 3010, 17, 55));
        itemsDAO.saveNew(new Weapon("Melted Mace", 10, 7, 15));
        itemsDAO.saveNew(new Weapon("Melted ice", 0, 0, 0));
        itemsDAO.saveNew(new Weapon("Mythical sword", 15, 20, 100));
        itemsDAO.saveNew(new Weapon("Basic Bow", 10, 0, 3));
        itemsDAO.saveNew(new Weapon("Basic Sword", 10, 3, 5));
        itemsDAO.saveNew(new Weapon("Cruel Crude Candle", 14, 3, 8));
        itemsDAO.saveNew(new Weapon("Phoenix Pike", 14, 33, 62));
        itemsDAO.saveNew(new Weapon("Gandalfs lost and found staff", 2000, 1, 1));
        itemsDAO.saveNew(new Weapon("Purging Pebble", 1, 39, 40));
    }

    private void initEnemies() {
        enemyDAO.saveNew(new Slime(6,2, healthHandler.calculateSlimeHealth(1), 1, new Random()));
        enemyDAO.saveNew(new Skeleton(8,2, healthHandler.calculateSkeletonHealth(1), 1));
        enemyDAO.saveNew(new Orc(2,4, healthHandler.calculateOrcHealth(1), 1));
        enemyDAO.saveNew(new Dragon(6,7, healthHandler.calculateDragonHealth(1), 1));
    }

    private void initLoots() {
        Loot loot1 = new Loot(4, 1);
        Loot loot2 = new Loot(1, 4);

        loot1.add(itemsDAO.getItemByName("Colossus Blade"));
        loot2.add(itemsDAO.getItemByName("Shadow Plate"));
        itemHandler.setLootGold(loot1);
        itemHandler.setLootGold(loot2);
        itemHandler.fillUpLoot(loot1);
        itemHandler.fillUpLoot(loot2);

        itemsDAO.saveNew(loot1);
        itemsDAO.saveNew(loot2);
    }

    private void initLevels() {
        levelDAO.saveNew(levelGenerator.levelOne(itemsDAO, enemyDAO));
    }
}
