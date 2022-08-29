package com.sigma.oilstation.entity;

import com.sigma.oilstation.entity.template.AbsUUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OutgoingCategory extends AbsUUID {

    @Column(nullable = false, unique = true)
    private String name;

    public OutgoingCategory(String name) {
        this.name = name;
    }
}
