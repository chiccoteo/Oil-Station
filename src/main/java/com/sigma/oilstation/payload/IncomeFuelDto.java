package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeFuelDto {
    private UUID id;
    private UUID fuelId;
    private double amount;
    private double incomePrice;
    private double salePrice;
    private double counter;
    private Timestamp incomeTime;
    private UUID employeeId;
    private boolean isDebt;
}
