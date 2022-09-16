package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class NotificationGetDTO {

    private UUID id;

    private String title;

    private String text;

    private boolean seen;

    private Date createdDate;

}
