package com.dq.carservice.controller.actions.car;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.repositories.CarRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCarAction extends Action {

    private View view;
    private CarRepository repo;

    @Override
    public String execute() {
        Car car = null;

        while (car == null) {
            long carId = view.getValidNumberPropertyCancellable("car id");

            car = repo.findById(carId);

            ViewHelper.displaySearchResult("Car", car, view);
        }

        if (view.getConfirmation("Do you want to delete this car?")) {
            repo.delete(car);
            return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
        } else {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        }
    }

    @Override
    public String getName() {
        return "Delete car";
    }
}