package com.sigma.oilstation.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class OutgoingGetDTO {

    private String name;

    private String description;

    private double amount;

    private String spenderFIO;

    private Timestamp outgoingTime;

    private String category;

    private Date lastModifiedDate;

    private String lastModifiedBy;

}
