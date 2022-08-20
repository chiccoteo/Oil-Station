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

    @ManyToOne
    private Device device;

    @Column(nullable = false)
    private double amountAtStartOfShift;

    private double amountAtEndOfShift;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fuel fuel;

    private double salePrice;

    private Timestamp reportTime;

    private boolean activeShift;


    @PrePersist
    @PreUpdate
    public void setSalePrice() {
        this.salePrice = fuel.getPrice();
    }

}
