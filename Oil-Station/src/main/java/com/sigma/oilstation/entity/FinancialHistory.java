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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class FinancialHistory extends AbsEntity {
    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Timestamp time;

    @ManyToOne(fetch = FetchType.LAZY)
    private FinancialCategory category;
}
