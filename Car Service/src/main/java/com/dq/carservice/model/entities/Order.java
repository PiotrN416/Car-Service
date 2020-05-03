package com.dq.carservice.model.entities;

import com.dq.carservice.model.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order extends Entity {

    public enum Status {NOT_STARTED, IN_PROGRESS, FINISHED}

    @Getter
    @Setter
    private Car car;

    @Getter
    @Setter
    private Client client;

    @Getter
    @Setter
    private List<Employee> workers;

    @Getter
    @Setter
    private Date reportedDate;

    @Getter
    @Setter
    private Date startDate;

    @Getter
    @Setter
    private Date endDate;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    private String description;

    @Override
    public List<String> getDetails() {
        ArrayList<String> details = new ArrayList<>();
        details.add("Id: " + getId());
        details.add("Description: " + getDescription());
        details.add("Status: " + getStatus());
        details.add("Reported: " + getReportedDate());
        details.add("Client: " + getClient());
        details.add("Car: " + getCar());
        return details;
    }
}
