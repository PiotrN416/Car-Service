package com.dq.carservice.controller.actions;

import com.dq.carservice.controller.Action;
import com.dq.carservice.helpers.utils.ConstraintsUtil;
import com.dq.carservice.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitAction extends Action {

    private View view;

    @Override
    public String execute() {
        view.setAppRunning(false);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
