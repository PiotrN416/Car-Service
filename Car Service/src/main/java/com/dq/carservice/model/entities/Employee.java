package com.dq.carservice.model.entities;

import com.dq.carservice.model.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends Entity {

    public enum Position {MECHANIC, OFFICE}

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String contact;

    @Getter
    @Setter
    private Position position;
}
