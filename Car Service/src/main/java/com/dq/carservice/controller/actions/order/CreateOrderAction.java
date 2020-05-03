package com.dq.carservice.controller.actions.order;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.model.entities.Car;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.entities.Order;
import com.dq.carservice.model.repositories.CarRepository;
import com.dq.carservice.model.repositories.ClientRepository;
import com.dq.carservice.model.repositories.OrderRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class CreateOrderAction extends Action {

    private View view;
    private OrderRepository orderRepo;
    private ClientRepository clientRepo;
    private CarRepository carRepo;

    @Override
    public String execute() {
        Order order = new Order();

        String desc = view.getPropertyCancellable("Description");
        order.setDescription(desc);

        String fName = view.getPropertyCancellable("Client name");
        String lName = view.getPropertyCancellable("Client last name");
        String contact = view.getPropertyCancellable("Client contact");

        Client client = clientRepo.findOrCreateNew(fName, lName, contact);

        String cProd = view.getPropertyCancellable("Car producer");
        String cModel = view.getPropertyCancellable("Car model");
        String cTable = view.getPropertyCancellable("Car table");

        Car car = carRepo.findOrCreateNew(cProd, cModel, cTable);

        order.setClient(client);
        order.setCar(car);
        order.setReportedDate(new Date());
        order.setStatus(Order.Status.NOT_STARTED);

        if (view.getConfirmation("Save order?")) {
            orderRepo.create(order);
            return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
        } else {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        }
    }

    @Override
    public String getName() {
        return "Create order";
    }
}
