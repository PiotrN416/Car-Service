package com.dq.carservice.helpers.utils;

import com.dq.carservice.model.Entity;
import com.dq.carservice.view.View;

import java.util.List;

public class ViewHelper {
    public static void displaySearchResults(String groupName, List<Entity> results, View view) {
        view.showMessage("Found " + groupName + ": " + results.size());
        for (Entity entity : results) {
            view.showSubMessage(entity.toString());
            displayDetails(entity, view);
        }
    }

    public static void displaySearchResult(String groupName, Entity result, View view) {
        view.showMessage("Found " + groupName + ": " + result);
        displayDetails(result, view);
    }

    private static void displayDetails(Entity entity, View view) {
        if (entity == null || entity.getDetails().size() == 0) {
            return;
        }
        view.showSubMessage("Details:");
        for (String detail : entity.getDetails()) {
            view.showSubMessage(detail);
        }
    }
}
