package com.dq.carservice.helpers.utils;

public class IdsService {

    private static long idCount = 1;

    public static long getNext() {
        return idCount++;
    }

    public static void setCurrentIndicator(long id) {
        idCount = id;
    }

}
