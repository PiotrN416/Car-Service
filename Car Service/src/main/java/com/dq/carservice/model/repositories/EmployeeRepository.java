package com.dq.carservice.model.repositories;

import com.dq.carservice.model.EntityRepository;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.entities.Employee;

public class EmployeeRepository extends EntityRepository<Employee> {

    public EmployeeRepository(PersistenceManager pm) {
        super(pm);
    }

}
