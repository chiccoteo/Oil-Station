package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class FuelReport extends AbsUUID {

    @ManyToOne(fetch = FetchType.LAZY)
    private User employee;

    @Column(nullable = false)
    private double amountAtStartOfShift;

    private double amountAtEndOfShift;

    @ManyToOne
    private Fuel fuel;

    @Column(nullable = false)
    private Double salePrice;

    private Timestamp reportTime;

    private boolean activeShift;

    public FuelReport(User employee, double amountAtStartOfShift, Fuel fuel, Double salePrice, Timestamp reportTime, boolean activeShift) {
        this.employee = employee;
        this.amountAtStartOfShift = amountAtStartOfShift;
        this.fuel = fuel;
        this.salePrice = salePrice;
        this.reportTime = reportTime;
        this.activeShift = activeShift;
    }
}
