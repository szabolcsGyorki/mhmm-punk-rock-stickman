package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.dao.PlayerDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.EnemyDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.LevelDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.PlayerDAOImpl;
import com.codecool.mhmm.stickman.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private ItemsDAO itemsDAO = Mockito.mock(ItemsDAOImpl.class);
    private EnemyDAO enemyDao = Mockito.mock(EnemyDAOImpl.class);
    private LevelDAO levelDao = Mockito.mock(LevelDAOImpl.class);
    private PlayerDAO playerDAO = Mockito.mock(PlayerDAOImpl.class);
    private HealthHandler healthHandler = Mockito.mock(HealthHandler.class);
    private LevelGenerator levelGenerator = Mockito.mock(LevelGenerator.class);
    private MoveHandler moveHandler = Mockito.mock(MoveHandler.class);
    private ItemHandler itemHandler = Mockito.mock(ItemHandler.class);
    private FightHandler fightHandler = Mockito.mock(FightHandler.class);

    @Test
    void sanityCheck() {
        Game game = new Game(itemsDAO, enemyDao, levelDao, playerDAO, healthHandler, levelGenerator,
                moveHandler, itemHandler, fightHandler);
        assertNotNull(game);
    }

}