package com.dq.carservice.model.repositories;

import com.dq.carservice.model.EntityRepository;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.persistence.QuerySpec;
import com.dq.carservice.model.persistence.SearchCondition;

import java.util.Collections;
import java.util.List;

public class CarRepository extends EntityRepository<Car> {

    public CarRepository(PersistenceManager pm) {
        super(pm);
    }

    public List<Car> findByModel(String model) {
        return read(new QuerySpec(
                Car.class,
                new SearchCondition("getModel", model)
        ));
    }

    public List<Car> findByProducer(String producer) {
        return read(new QuerySpec(
                Car.class,
                new SearchCondition("getProducer", producer)
        ));
    }

    public List<Car> findByTable(String table) {
        return read(new QuerySpec(
                Car.class,
                new SearchCondition("getTable", table)
        ));
    }

    public List<Car> findAll() {
        return read(new QuerySpec(Car.class));
    }

    public Car findById(Long id) {
        return readUnique(new QuerySpec(
                Car.class,
                Collections.singletonList(new SearchCondition("getId", id))
        ));
    }

    public Car findOrCreateNew(String producer, String model, String table) {
        Car car = readUnique(new QuerySpec(
                Car.class,
                new SearchCondition("getProducer", producer),
                new SearchCondition("getModel", model),
                new SearchCondition("getTable", table)
        ));

        return car != null ? car : create(new Car(producer, model, table, null, null));
    }
}
