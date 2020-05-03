package com.dq.carservice.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {

    @Getter
    @Setter
    private String fieldGetter;

    @Getter
    @Setter
    private Object fieldValue;
}
