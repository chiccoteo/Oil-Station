package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationGetDTO {

    private UUID id;

    private String text;

}
