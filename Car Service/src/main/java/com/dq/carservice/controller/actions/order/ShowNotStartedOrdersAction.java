package com.dq.carservice.controller.actions.order;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.EntitiesHelper;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Order;
import com.dq.carservice.model.repositories.OrderRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowNotStartedOrdersAction extends Action {

    private View view;
    private OrderRepository orderRepo;

    @Override
    public String execute() {
        List<Order> notStarted = orderRepo.findNotStarted();

        ViewHelper.displaySearchResults(
                "orders", EntitiesHelper.ordersToEntites(notStarted), view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Show not started orders";
    }
}
