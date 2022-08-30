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
public class IncomeFuelPostDto {

    private UUID fuelId;
    @Min(value = 0, message = "not negative")
    private double amount;
    @Min(value = 0, message = "not negative")
    private double incomePrice;
    @Min(value = 0, message = "not negative")
    private double salePrice;
    @Min(value = 0, message = "not negative")
    private double counter;
    private Timestamp incomeTime;
    private UUID employeeId;
    private boolean debt;
}
