package com.dq.carservice.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class QuerySpec {

    public QuerySpec(Class<?> resultType) {
        this.resultType = resultType;
    }

    public QuerySpec(Class<?> resultType, SearchCondition... conditions) {
        this.resultType = resultType;
        this.conditions.addAll(Arrays.asList(conditions));
    }

    @Getter
    @Setter
    private Class<?> resultType;

    @Getter
    @Setter
    private List<SearchCondition> conditions = new ArrayList<>();
}
