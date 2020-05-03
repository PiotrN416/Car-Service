package com.dq.carservice.controller.actions.car;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.repositories.CarRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCarAction extends Action {

    private View view;
    private CarRepository repo;

    @Override
    public String execute() {
        Car car = new Car();

        String producer = view.getPropertyCancellable("Producer");
        car.setProducer(producer);

        String model = view.getPropertyCancellable("Model");
        car.setModel(model);

        String table = view.getPropertyCancellable("Table");
        car.setTable(table);

        Car car1 = repo.create(car);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create car";
    }
}
