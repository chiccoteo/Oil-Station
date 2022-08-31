package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsEntity;
import com.sigma.oilstation.entity.template.AbsLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Measurement extends AbsLong {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean deleted;

}
