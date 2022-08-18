package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
