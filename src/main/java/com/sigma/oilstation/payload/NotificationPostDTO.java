package com.sigma.oilstation.payload;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NotificationPostDTO {

    private String title;

    private String text;

    private UUID fuelReportId;

}