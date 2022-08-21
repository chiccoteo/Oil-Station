package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelReportPostDto {
    private UUID employeeId;
    private double amountAtStartOfShift;
    private UUID fuelId;
    private double salePrice;
    private Timestamp reportTime;
    private boolean activeShift;
}
