package com.dq.carservice.view;

import com.dq.carservice.controller.SelectOption;

import java.util.List;

public interface View {

    void showMessage(String message);

    void showSubMessage(String message);

    String getProperty(String propertyName);

    String getPropertyCancellable(String propertyName);

    long getValidNumberPropertyCancellable(String propertyName);

    SelectOption selectFromOptions(String setName, List<SelectOption> options);

    boolean getConfirmation(String message);

    boolean isAppRunning();

    void setAppRunning(boolean flag);
}
