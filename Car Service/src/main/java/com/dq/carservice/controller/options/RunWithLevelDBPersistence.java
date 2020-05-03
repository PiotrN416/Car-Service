package com.dq.carservice.controller.options;

import com.dq.carservice.controller.SelectOption;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.persistence.LevelDBPersistenceManager;

public class RunWithLevelDBPersistence extends SelectOption<PersistenceManager> {

    @Override
    public PersistenceManager execute() {
        return new LevelDBPersistenceManager();
    }

    @Override
    public String getOptionLabel() {
        return "LevelDB";
    }
}
