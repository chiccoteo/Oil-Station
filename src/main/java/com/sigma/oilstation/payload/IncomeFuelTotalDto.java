package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeFuelTotalDto {
    private double totalSumForIncomePrice;
    private double totalSumForSalePrice;
    private double totalAmount;
    private List<IncomeFuelDto> incomeFuelDtoList;
}
