package com.dq.carservice.model.entities;

import com.dq.carservice.model.Entity;
import com.dq.carservice.model.entities.subParts.Engine;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car extends Entity {

    @Getter
    @Setter
    private String producer;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private String table;

    @Getter
    @Setter
    private Engine engine;

    @Getter
    @Setter
    private Client owner;

    @Override
    public List<String> getDetails() {
        ArrayList<String> details = new ArrayList<>();
        details.add("Id: " + getId());
        details.add("Producer: " + getProducer());
        details.add("Model: " + getModel());
        details.add("Table: " + getTable());
        return details;
    }
}
