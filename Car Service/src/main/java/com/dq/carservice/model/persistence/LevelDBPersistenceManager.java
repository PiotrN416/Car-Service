package com.dq.carservice.model.persistence;

import com.dq.carservice.helpers.utils.PersistenceHelper;
import com.dq.carservice.model.Entity;
import com.dq.carservice.model.PersistenceManager;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

public class LevelDBPersistenceManager implements PersistenceManager {

    private DB levelDBStore;

    public LevelDBPersistenceManager() {
        try {
            Options options = new Options();
            levelDBStore = factory.open(new File("levelDBStore"), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Entity create(Entity entity) {
        levelDBStore.put(serialize(entity.getId()), serialize(entity));
        return (Entity) deserializeObject(levelDBStore.get(serialize(entity.getId())));
    }

    @Override
    public List<Entity> read(QuerySpec querySpec) {
        List<Entity> results = new ArrayList<>();

        DBIterator iterator = levelDBStore.iterator();

        while (iterator.hasNext()) {

            byte[] serializedObject = iterator.peekNext().getValue();

            Entity entity;
            try {
                entity = (Entity) deserializeObject(serializedObject);
            } catch (Exception e) {
                iterator.next();
                continue;
            }

            if (entity.getClass() == querySpec.getResultType()
                    && PersistenceHelper.isMatchingAllConditions(entity, querySpec.getConditions())) {
                results.add(entity);
            }
            iterator.next();
        }

        try {
            iterator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public Entity update(Entity entity) {
        return create(entity);
    }

    @Override
    public boolean delete(Entity entity) {
        levelDBStore.delete(serialize(entity.getId()));
        return levelDBStore.get(serialize(entity.getId())) == null;
    }

    private byte[] serialize(Serializable object) {
        return SerializationUtils.serialize(object);
    }

    private Object deserializeObject(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        levelDBStore.close();
    }
}
