package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelReportDto {
    private UUID id;
    private UUID employeeId;
    @Min(value = 0,message = "not negative")
    private double amountAtStartOfShift;
    @Min(value = 0,message = "not negative")
    private double amountAtEndOfShift;
    private UUID fuelId;
    @Min(value = 0,message = "not negative")
    private double salePrice;
    private Timestamp reportTime;
    private boolean activeShift;
}