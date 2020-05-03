package com.dq.carservice.model;

import com.dq.carservice.helpers.utils.IdsService;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Entity implements Serializable {

    @Getter
    private long id;

    public Entity() {
        id = IdsService.getNext();
    }

    public List<String> getDetails() {
        return Collections.emptyList();
    }
}
