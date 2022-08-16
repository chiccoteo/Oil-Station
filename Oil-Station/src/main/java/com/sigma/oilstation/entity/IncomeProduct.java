package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IncomeProduct extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private double amount;

    private double incomePrice;

    private double outcomePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeCounter employeeCounter;

    private Timestamp incomeTime;


    @Column(nullable = false)
    private boolean isDebt;
}
