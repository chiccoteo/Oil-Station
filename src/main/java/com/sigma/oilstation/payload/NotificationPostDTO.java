package com.sigma.oilstation.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationPostDTO {

    private String text;

}