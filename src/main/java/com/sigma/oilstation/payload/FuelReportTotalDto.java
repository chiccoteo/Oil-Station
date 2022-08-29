package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelReportTotalDto {
    private double totalSum;
    private double totalAmount;
    private List<FuelReportDto> fuelReportDtoList;
}
