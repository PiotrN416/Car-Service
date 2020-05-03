package com.dq.carservice.model.repositories;

import com.dq.carservice.model.EntityRepository;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.entities.Order;
import com.dq.carservice.model.persistence.QuerySpec;
import com.dq.carservice.model.persistence.SearchCondition;

import java.util.List;


public class OrderRepository extends EntityRepository<Order> {

    public OrderRepository(PersistenceManager pm) {
        super(pm);
    }

    public Order findInProgressById(Long id) {
        return readUnique(new QuerySpec(
                Order.class,
                new SearchCondition("getId", id),
                new SearchCondition("getStatus", Order.Status.IN_PROGRESS)
        ));
    }

    public Order findNotStartedById(Long id) {
        return readUnique(new QuerySpec(
                Order.class,
                new SearchCondition("getId", id),
                new SearchCondition("getStatus", Order.Status.NOT_STARTED)
        ));
    }

    public List<Order> findAll() {
        return read(new QuerySpec(Order.class));
    }

    public List<Order> findFinished() {
        return read(new QuerySpec(
                Order.class,
                new SearchCondition("getStatus", Order.Status.FINISHED)
        ));
    }

    public List<Order> findInProgress() {
        return read(new QuerySpec(
                Order.class,
                new SearchCondition("getStatus", Order.Status.IN_PROGRESS)
        ));
    }

    public List<Order> findNotStarted() {
        return read(new QuerySpec(
                Order.class,
                new SearchCondition("getStatus", Order.Status.NOT_STARTED)
        ));
    }

    public List<Order> findOfClient(Client client) {
        return read(new QuerySpec(
                Order.class,
                new SearchCondition("getClient", client)
        ));
    }
}
