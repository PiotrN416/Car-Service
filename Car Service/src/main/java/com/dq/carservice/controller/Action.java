package com.dq.carservice.controller;

public abstract class Action extends SelectOption<String> {

    public Action() {
        super();
    }

    public abstract String getName();

    @Override
    public final String getOptionLabel() {
        return getName();
    }

}
