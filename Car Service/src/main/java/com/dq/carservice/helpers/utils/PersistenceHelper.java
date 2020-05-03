package com.dq.carservice.helpers.utils;

import com.dq.carservice.model.Entity;
import com.dq.carservice.model.persistence.SearchCondition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PersistenceHelper {
    public static boolean isMatchingAllConditions(Entity entity, List<SearchCondition> conditions) {
        try {
            for (SearchCondition sc : conditions) {

                Method method = entity.getClass().getMethod(sc.getFieldGetter());
                Object value = method.invoke(entity);

                if (!value.toString().equalsIgnoreCase(sc.getFieldValue().toString())) {
                    return false;
                }

            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }
        return true;
    }
}
