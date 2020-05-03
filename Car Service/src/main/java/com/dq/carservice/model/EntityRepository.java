package com.dq.carservice.model;

import com.dq.carservice.helpers.exceptions.NotUniqueException;
import com.dq.carservice.model.persistence.QuerySpec;

import java.util.List;
import java.util.stream.Collectors;

public class EntityRepository<T extends Entity> {

    private PersistenceManager pm;

    public EntityRepository(PersistenceManager pm) {
        this.pm = pm;
    }

    public T create(T entity) {
        return (T) pm.create(entity);
    }

    public List<T> read(QuerySpec qs) {
        return pm.read(qs).stream()
                .map(e -> (T) e)
                .collect(Collectors.toList());
    }

    public T readUnique(QuerySpec qs) throws NotUniqueException {
        List<T> all = read(qs);

        if (all.size() == 0) {
            return null;
        } else if (all.size() == 1) {
            return all.get(0);
        } else {
            throw new NotUniqueException("Found " + all.size() + " results, but expected one");
        }
    }

    public T update(T entity) {
        return (T) pm.update(entity);
    }

    public boolean delete(T entity) {
        return pm.delete(entity);
    }
}
