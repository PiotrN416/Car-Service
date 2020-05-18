package com.dq.carservice.controller.actions.client;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.helpers.utils.ViewHelper;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.repositories.ClientRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowAllClientsAction extends Action {

    private View view;
    private ClientRepository clientRepo;

    @Override
    public String execute() {
        List<Client> clients = clientRepo.findAll();

        ViewHelper.displaySearchResults("clients", clients, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Show all clients";
    }
}
