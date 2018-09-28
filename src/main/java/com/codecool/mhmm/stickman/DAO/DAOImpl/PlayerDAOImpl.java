package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.PlayerDAO;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class PlayerDAOImpl extends BaseDaoImpl implements PlayerDAO {

    PlayerDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Player findById(long id) {
        return em.find(Player.class, id);
    }

    @Override
    public List<Player> getAll() {
        TypedQuery<Player> query = em.createNamedQuery("Player.getAll", Player.class);
        return query.getResultList();
    }

    @Override
    public void update(Object player, String field, Object value) {
        transaction.begin();
        CriteriaUpdate<Player> cq = cb.createCriteriaUpdate(Player.class);
        Root<Player> playerRoot = cq.from(Player.class);
        cq.set(field, value)
                .where(cb.equal(playerRoot.get("id"), ((Player)player).getId()));
        Query query = em.createQuery(cq);
        query.executeUpdate();
//        em.refresh(player);
        transaction.commit();
    }

    @Override
    public void saveNew(Object player) {
        transaction.begin();
        em.persist(player);
        transaction.commit();
    }

    @Override
    public Player getPlayerByName(String name) {
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> playerRoot = cq.from(Player.class);
        ParameterExpression p = cb.parameter(String.class);
        cq.select(playerRoot).where(cb.equal(playerRoot.get("name"), p));
        TypedQuery<Player> query = em.createQuery(cq);
        query.setParameter(p, name);
        return query.getSingleResult();
    }

    @Override
    public List<GameObject> getModifiedByPlayer(Player player) {
        return null;
    }

    @Override
    public List<GameObject> getModifiedByPlayer(String playerName) {
        return null;
    }

    @Override
    public List<GameObject> getModifiedByPlayer(long playerID) {
        return null;
    }

    @Override
    public void updateModifiedByPlayer(Player player) { }

    @Override
    public void updatePlayerItems(Player player) { }
}
