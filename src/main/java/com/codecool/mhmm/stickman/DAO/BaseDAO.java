package com.codecool.mhmm.stickman.DAO;

import java.util.List;

public interface BaseDAO <T> {

    /**
     * @param id: id of the entity
     * @return: one entity as object
     */
    T findById(long id);

    /**
     * @return: a list of all entities as objects
     */
    List<T> getAll();

    /**
     * @param entity: the object to update
     * @param field: name of the field to update (from the POJO, not DB)
     * @param value: the new value of the given field
     */
    void update(T entity, String field, T value);
    void saveNew(T entity);
}
