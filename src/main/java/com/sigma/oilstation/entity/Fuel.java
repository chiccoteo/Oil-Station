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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Fuel extends AbsEntity {

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Measurement incomeMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Measurement outcomeMeasurement;


    @Column(nullable = false)
    private boolean deleted;
}
