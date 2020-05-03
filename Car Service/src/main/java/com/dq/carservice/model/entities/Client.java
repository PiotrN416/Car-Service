package com.dq.carservice.model.entities;

import com.dq.carservice.model.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client extends Entity {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String contact;

    @Override
    public List<String> getDetails() {
        ArrayList<String> details = new ArrayList<>();
        details.add("Id: " + getId());
        details.add("Name: " + getFirstName());
        details.add("Last Name: " + getLastName());
        details.add("Contact: " + getContact());
        return details;
    }
}
