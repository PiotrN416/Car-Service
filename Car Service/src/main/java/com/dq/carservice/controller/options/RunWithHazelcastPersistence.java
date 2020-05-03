package com.dq.carservice.controller.options;

import com.dq.carservice.controller.SelectOption;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.persistence.HazelcastPersistenceManager;

public class RunWithHazelcastPersistence extends SelectOption<PersistenceManager> {

    @Override
    public String getOptionLabel() {
        return "Hazelcast";
    }

    @Override
    public PersistenceManager execute() {
        return new HazelcastPersistenceManager();
    }
}
