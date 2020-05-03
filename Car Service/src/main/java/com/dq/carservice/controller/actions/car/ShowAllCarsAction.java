package com.dq.carservice.controller.actions.car;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.EntitiesHelper;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.repositories.CarRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowAllCarsAction extends Action {

    private View view;
    private CarRepository carRepo;

    @Override
    public String execute() {
        List<Car> cars = carRepo.findAll();

        ViewHelper.displaySearchResults(
                "cars", EntitiesHelper.carsToEntities(cars), view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Show all cars";
    }
}
