package com.dq.carservice.controller.actions.client;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.model.entities.Client;
import com.dq.carservice.model.repositories.ClientRepository;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClientAction extends Action {

    private View view;
    private ClientRepository clientRepo;

    @Override
    public String execute() {
        Client client = new Client();

        String fName = view.getPropertyCancellable("First name");
        client.setFirstName(fName);

        String lName = view.getPropertyCancellable("Last name");
        client.setLastName(lName);

        String contact = view.getPropertyCancellable("Contact");
        client.setContact(contact);

        clientRepo.create(client);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create client";
    }
}
