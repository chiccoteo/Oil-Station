package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelReportPostDto {
    private UUID employeeId;
    @Min(value = 0,message = "not negative")
    private double amountAtStartOfShift;
    private UUID fuelId;
    @Min(value = 0,message = "not negative")
    @NotNull
    private Double salePrice;
    private Timestamp reportTime;
}
