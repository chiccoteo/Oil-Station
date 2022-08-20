package com.sigma.oilstation.payload;

import com.sigma.oilstation.entity.Fuel;
import com.sigma.oilstation.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeFuelReqDto {
    @ManyToOne(fetch = FetchType.LAZY)
    private Fuel fuel;

    @Column(nullable = false)
    private double amount;

    private double incomePrice;

    private double salePrice;

    private double counter;

    private Timestamp incomeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User employee;

    @Column(nullable = false)
    private boolean isDebt;
}
