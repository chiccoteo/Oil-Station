package com.sigma.oilstation.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OutgoingGetDTOForReport {

    private UUID id;

    private String name;

    private String description;

    private double amount;

    private String spenderFIO;

    private UUID spenderId;

    private Timestamp outgoingTime;

    private String category;

}
