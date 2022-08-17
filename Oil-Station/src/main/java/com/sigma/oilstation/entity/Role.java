package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsLong;
import com.sigma.oilstation.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AbsLong {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType type;
}
