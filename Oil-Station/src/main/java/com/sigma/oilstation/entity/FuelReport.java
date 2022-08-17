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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class FuelReport extends AbsUUID {

    @ManyToOne(fetch = FetchType.LAZY)
    private User employee;

    @Column(nullable = false)
    private double amountAtStartOfShift;

    private double amountAtEndOfShift;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fuel fuel;

    private Timestamp reportTime;

    private boolean isActiveShift;

}
