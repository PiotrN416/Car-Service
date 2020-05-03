package com.dq.carservice.model;

import com.dq.carservice.model.persistence.QuerySpec;

import java.util.List;

public interface PersistenceManager {

    Entity create(Entity entity);

    List<Entity> read(QuerySpec querySpec);

    Entity update(Entity entity);

    boolean delete(Entity entity);
}
