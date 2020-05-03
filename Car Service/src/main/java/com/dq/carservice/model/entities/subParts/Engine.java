package com.dq.carservice.model.entities.subParts;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Engine implements Serializable {

    @Getter
    @Setter
    private float capacity;

    @Getter
    @Setter
    private float power;

    @Getter
    @Setter
    private String fullName;
}
