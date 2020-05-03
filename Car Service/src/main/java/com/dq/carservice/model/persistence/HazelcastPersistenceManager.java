package com.dq.carservice.model.persistence;

import com.dq.carservice.helpers.utils.PersistenceHelper;
import com.dq.carservice.model.Entity;
import com.dq.carservice.model.PersistenceManager;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HazelcastPersistenceManager implements PersistenceManager {
    private static HazelcastInstance hz;

    public HazelcastPersistenceManager() {
        hz = Hazelcast.newHazelcastInstance();
    }

    @Override
    public Entity create(Entity entity) {
        IMap<Long, Entity> map = hz.getMap(entity.getClass().getSimpleName());
        map.put(entity.getId(), entity);
        return map.get(entity.getId());
    }

    @Override
    public List<Entity> read(QuerySpec querySpec) {
        IMap<Long, Entity> map = hz.getMap(querySpec.getResultType().getSimpleName());

        List<Entity> all = convertToList(map);

        if (querySpec.getConditions().size() == 0) {
            return all;
        }

        List<Entity> matching = new ArrayList<>();
        for (Entity entity : all) {
            if (PersistenceHelper.isMatchingAllConditions(entity, querySpec.getConditions())) {
                matching.add(entity);
            }
        }

        return matching;
    }

    @Override
    public Entity update(Entity entity) {
        return create(entity);
    }

    @Override
    public boolean delete(Entity entity) {
        IMap<Long, Entity> map = hz.getMap(entity.getClass().getSimpleName());
        map.delete(entity.getId());
        return map.get(entity.getId()) == null;
    }

    private List<Entity> convertToList(IMap<Long, Entity> map) {
        ArrayList<Entity> result = new ArrayList<>();

        Set<Long> allIds = map.localKeySet();
        for (Long id : allIds) {
            result.add(map.get(id));
        }

        return result;
    }
}
