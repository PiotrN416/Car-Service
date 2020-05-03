package com.dq.carservice;

import com.dq.carservice.controller.SelectOption;
import com.dq.carservice.controller.actions.ExitAction;
import com.dq.carservice.controller.actions.car.*;
import com.dq.carservice.controller.actions.client.CreateClientAction;
import com.dq.carservice.controller.actions.client.ShowAllClientsAction;
import com.dq.carservice.controller.actions.employee.CreateEmployeeAction;
import com.dq.carservice.controller.actions.order.*;
import com.dq.carservice.controller.options.RunWithHazelcastPersistence;
import com.dq.carservice.controller.options.RunWithLevelDBPersistence;
import com.dq.carservice.helpers.exceptions.CancellingOperationException;
import com.dq.carservice.helpers.exceptions.CarServiceUncheckedException;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.model.PersistenceManager;
import com.dq.carservice.model.repositories.CarRepository;
import com.dq.carservice.model.repositories.ClientRepository;
import com.dq.carservice.model.repositories.EmployeeRepository;
import com.dq.carservice.model.repositories.OrderRepository;
import com.dq.carservice.view.ConsoleView;
import com.dq.carservice.view.View;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<SelectOption> registeredActions;
    private static PersistenceManager persistence;
    private static View view;

    public static void main(String[] args) {
        view = new ConsoleView();
        view.showMessage("Starting application");

        persistence = initializePersistence();
        registeredActions = initializeActions();

        while (view.isAppRunning()) {
            SelectOption action = view.selectFromOptions("action", registeredActions);
            String result = executeCancellableAction(action);
            view.showMessage(result);
        }

        view.showMessage("Good bye!");
    }

    private static String executeCancellableAction(SelectOption action) {
        try {
            return (String) action.execute();
        } catch (CancellingOperationException e) {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        } catch (CarServiceUncheckedException e) {
            return ConstraintsUtil.OPERATION_FAILED_MESSAGE;
        }
    }

    private static PersistenceManager initializePersistence() {
        List<SelectOption> options = new ArrayList<>();

        options.add(new RunWithHazelcastPersistence());
        options.add(new RunWithLevelDBPersistence());

        SelectOption option = view.selectFromOptions("persistence handlers", options);

        return (PersistenceManager) option.execute();
    }

    private static List<SelectOption> initializeActions() {
        CarRepository carRepo = new CarRepository(persistence);
        ClientRepository clientRepo = new ClientRepository(persistence);
        EmployeeRepository employRepo = new EmployeeRepository(persistence);
        OrderRepository orderRepo = new OrderRepository(persistence);

        ArrayList<SelectOption> actions = new ArrayList<>();
        actions.add(new CreateCarAction(view, carRepo));
        actions.add(new DeleteCarAction(view, carRepo));
        actions.add(new FindCarByModelAction(view, carRepo));
        actions.add(new FindCarByProducerAction(view, carRepo));
        actions.add(new FindCarByTableAction(view, carRepo));
        actions.add(new ShowAllCarsAction(view, carRepo));

        actions.add(new CreateClientAction(view, clientRepo));
        actions.add(new ShowAllClientsAction(view, clientRepo));

        actions.add(new CreateEmployeeAction(view, employRepo));

        actions.add(new CreateOrderAction(view, orderRepo, clientRepo, carRepo));
        actions.add(new MarkOrderFinishedAction(view, orderRepo));
        actions.add(new MarkOrderStartedAction(view, orderRepo));
        actions.add(new ShowAllOrdersAction(view, orderRepo));
        actions.add(new ShowFinishedOrdersAction(view, orderRepo));
        actions.add(new ShowInProgressOrdersAction(view, orderRepo));
        actions.add(new ShowNotStartedOrdersAction(view, orderRepo));
        actions.add(new ShowOrdersOfClientAction(view, orderRepo, clientRepo));

        actions.add(new ExitAction(view));

        return actions;
    }
}
