package com.sigma.oilstation.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class FuelReportCurrent {

    private UUID id;

    private UUID branchId;

    private UUID fuelId;

    private double amount;

    private Long measurementId;

}
