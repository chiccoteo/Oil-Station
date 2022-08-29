package com.sigma.oilstation.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutgoingGetDTOForReport {

    private String name;

    private String description;

    private double amount;

    private String spenderFIO;

    private Timestamp outgoingTime;

    private String category;

}
