package com.dq.carservice.helpers.utils;

import com.dq.carservice.model.Entity;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

public class EntitiesHelper {

    public static List<Entity> carsToEntities(List<Car> list) {
        return list.stream()
                .map(c -> (Entity) c)
                .collect(Collectors.toList());
    }

    public static List<Entity> clientsToEntites(List<Client> list) {
        return list.stream()
                .map(c -> (Entity) c)
                .collect(Collectors.toList());
    }


    public static List<Entity> ordersToEntites(List<Order> list) {
        return list.stream()
                .map(c -> (Entity) c)
                .collect(Collectors.toList());
    }
}
