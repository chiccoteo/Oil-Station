package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Debt extends AbsUUID {

    @Column(nullable = false)
    private String borrower;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private User lenderOrBorrower;

    @ManyToOne
    private Supplier lender;

    private Timestamp givenTime;

    private Timestamp returnTime;

    private boolean isGiven;

}
