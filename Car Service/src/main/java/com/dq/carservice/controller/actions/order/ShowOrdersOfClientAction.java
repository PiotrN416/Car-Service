package com.dq.carservice.controller.actions.order;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.entities.Order;
import com.dq.carservice.model.repositories.ClientRepository;
import com.dq.carservice.model.repositories.OrderRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowOrdersOfClientAction extends Action {

    private View view;
    private OrderRepository orderRepo;
    private ClientRepository clientRepo;

    @Override
    public String execute() {
        Client client = null;

        while (client == null) {
            String fName = view.getPropertyCancellable("Client name");
            String lName = view.getPropertyCancellable("Client last name");
            String contact = view.getPropertyCancellable("Client contact");

            client = clientRepo.findByCredentials(fName, lName, contact);

            ViewHelper.displaySearchResult("Client", client, view);
        }

        List<Order> orders = orderRepo.findOfClient(client);
        ViewHelper.displaySearchResults("Orders", orders, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Show orders of client";
    }
}
