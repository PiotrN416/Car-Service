package com.dq.carservice.controller.actions.order;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Order;
import com.dq.carservice.model.repositories.OrderRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class MarkOrderFinishedAction extends Action {

    private View view;
    private OrderRepository orderRepo;

    @Override
    public String execute() {
        Order order = null;

        while (order == null) {
            Long id = view.getValidNumberPropertyCancellable("order id");

            order = orderRepo.findInProgressById(id);

            ViewHelper.displaySearchResult("Order", order, view);
        }

        if (view.getConfirmation("Do you want to close the order?")) {
            order.setStatus(Order.Status.FINISHED);
            order.setEndDate(new Date());
            orderRepo.update(order);
            return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
        } else {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        }
    }

    @Override
    public String getName() {
        return "Mark order finished";
    }
}
