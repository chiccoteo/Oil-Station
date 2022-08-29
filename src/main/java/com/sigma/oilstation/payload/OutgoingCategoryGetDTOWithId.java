package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class OutgoingCategoryGetDTOWithId {

    private UUID id;

    private String name;

}
