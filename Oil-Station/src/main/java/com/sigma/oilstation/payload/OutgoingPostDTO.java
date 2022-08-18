package com.sigma.oilstation.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OutgoingPostDTO {

    private String name;

    private String description;

    private double amount;

    private UUID spenderId;

    private Timestamp outgoingTime;

    private String outgoingCategoryName;

}
