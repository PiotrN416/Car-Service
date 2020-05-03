package com.dq.carservice.controller;

import com.dq.carservice.helpers.utils.IdsService;
import lombok.Getter;

public abstract class SelectOption<T> {

    @Getter
    private long optionId;

    public SelectOption() {
        optionId = IdsService.getNext();
    }

    public abstract String getOptionLabel();

    public abstract T execute();

}
