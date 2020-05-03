package com.dq.carservice.controller.actions.employee;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.model.entities.Employee;
import com.dq.carservice.model.repositories.EmployeeRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEmployeeAction extends Action {

    private View view;
    private EmployeeRepository empRepo;

    @Override
    public String execute() {
        Employee employee = new Employee();

        String fName = view.getPropertyCancellable("First name");
        employee.setFirstName(fName);

        String lName = view.getPropertyCancellable("Last name");
        employee.setLastName(lName);

        String contact = view.getPropertyCancellable("Contact");
        employee.setContact(contact);

        employee.setPosition(Employee.Position.MECHANIC);

        empRepo.create(employee);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create employee";
    }
}
