package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelPostDto {
    private String name;
    private String type;
    private Double price;
    private Long incomeMeasurementId;
    private Long outcomeMeasurementId;
}
