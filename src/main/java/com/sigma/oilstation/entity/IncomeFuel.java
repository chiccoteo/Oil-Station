package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
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
public class IncomeFuel extends AbsUUID {

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
    private boolean debt;

}
